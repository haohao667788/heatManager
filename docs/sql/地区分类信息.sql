drop sequence ctytrigger if exists;
drop sequence dstid if exists;
drop sequence pjt_id if exists;
drop sequence cmt_id if exists;
drop sequence bld_id if exists;
drop sequence unt_id if exists;
drop sequence mch_id if exists;
drop sequence src_id if exists;

drop table unit_info if exists;
drop table building_info if exists;
drop table community_info if exists;
drop table project_info if exists;
drop table district_info if exists;
drop table heatsource_info if exists;
drop table machineset_info if exists;
drop table county_info if exists;

/** 区县表及其相关 */
create sequence cty_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table county_info (
       ctyid number(10)
       ,ctyname varchar2(20) not null
       ,comm varchar2(2000)
       ,primary key (ctyid)
);

create or replace trigger cty_trigger 
before insert on county_info 
for each row 
  begin 
    select cty_id.nextval into :new.ctyid from dual;
  end;
/
       

/** 大区表及其相关 */
create sequence dst_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table district_info (
       dstid number(10)
       ,dstname varchar2(40) not null
       ,ctyid number(6) not null
       ,desp varchar2(200)
       ,comm varchar2(2000)
       ,primary key (dstid)
       ,foreign key (ctyid) references county_info(ctyid)
);

create or replace trigger dst_trigger 
before insert on district_info 
for each row 
  begin 
    select dst_id.nextval into :new.dstid from dual;
  end;
/

/** 项目表及其相关 */
create sequence pjt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table project_info (
       pjtid number(10)
       ,pjtname varchar2(40) not null
       ,dstid number(8) not null
       ,start_date date not null
       ,desp varchar2(200)
       ,comm varchar2(2000)
       ,primary key (pjtid)
       ,foreign key (dstid) references district_info(dstid)
);

create or replace trigger pjt_trigger 
before insert on project_info 
for each row 
  begin 
    select pjt_id.nextval into :new.pjtid from dual;
  end;
/

/** 社区表及其相关 */
create sequence cmt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table community_info (
       cmtid number(10)
       ,pjtid number(10) not null
       ,cmtname varchar2(20) not null
       ,briefname varchar(10)
       ,cmtaddress varchar2(200) not null
       ,dstid number(10)
       ,gis varchar2(20)
       ,picaddress varhcar2(100)
       ,desp varchar2(200)
       ,comm varchar2(2000)
       ,primary key (cmtid)
       ,foreign key (pjtid) references project_info(pjtid)
       ,foreign key (dstid) references district_info(dstid)
);

create or replace trigger cmt_trigger 
before insert on community_info 
for each row 
  begin 
    select cmt_id.nextval into :new.cmtid from dual;
  end;
/

/** 热源（换热站）表及其相关 */
create sequence src_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/** 供热班组 待加 */
create table heatsource_info(
       srcid number(10)
       ,srcname varchar2(20) not null
       ,dstid number(10)
       ,srcaddress varchar2(200)
       ,heattype varchar2(20)
       ,desp varchar2(200)
       ,comm varchar2(2000)
       ,primary key (srcid)
       ,foreign key (dstid) references district_info(dstid)
);

create or replace trigger src_trigger 
before insert on heatsource_info 
for each row 
  begin 
    select src_id.nextval into :new.srcid from dual;
  end;
/

/** 机组表及其相关 */
create sequence mch_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/** 班组 缺少**/
create machineset_info(
       mchid number(10)
       ,mchname varchar2(20) not null
       ,srcid number(10) not null
       ,gis varchar2(20)
       ,primary key (mchid)
       ,foreign key (srcid) references heatsource_info(srcid)
);

create or replace trigger mch_trigger 
before insert on machineset_info 
for each row 
  begin 
    select mch_id.nextval into :new.mchid from dual;
  end;
/

/** 楼栋表及其相关 */
create sequence bld_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table building_info (
       bldid number(10)
       ,bldname varchar2(20) not null
       ,bldaddress varchar2(200) not null
       ,cmtid number(10) not null
       ,srcid number(10) not null
       ,heattype varchar2(10) not null
       ,gis varchar2(20)
       ,picaddress varchar2(100)
       ,desp varchar2(200)
       ,comm varchar2(2000)
       ,primary key (bldid)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (srcid) references heatsource_info(srcid)
);

create or replace trigger bld_trigger 
before insert on building_info 
for each row 
  begin 
    select bld_id.nextval into :new.bldid from dual;
  end;
/


/** 单元表及其相关 */
create sequence unt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table unit_info (
       untid number(10)
       ,untname varchar2(20) not null
       ,bldid number(10) not null
       ,mchid number(10) not null
       ,gis varchar2(20)
       ,picaddress varchar2(100)
       ,comm varchar2(2000)
       ,primary key (untid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (mchid) references machineset_info(mchid)
);

create or replace trigger unt_trigger 
before insert on unit_info 
for each row 
  begin 
    select unt_id.nextval into :new.untid from dual;
  end;
/
