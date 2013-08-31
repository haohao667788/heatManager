drop sequence dst_id;
drop sequence cty_id;
drop sequence pjt_id;
drop sequence cmt_id; 
drop sequence bld_id; 
drop sequence unt_id; 
drop sequence mch_id; 
drop sequence src_id; 

drop table unit_info;
drop table building_info;
drop table community_info;
drop table project_info;
drop table district_info;
drop table heatsource_info;
drop table machineset_info;
drop table county_info;

/** ���ر������ */
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

       

/** ����������� */
create sequence dst_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table district_info (
       dstid number(10)
       ,dstname varchar2(40) not null
       ,comm varchar2(2000)
       ,primary key (dstid)
);


/** ��Ŀ������� */
create sequence pjt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table project_info (
       pjtid number(10)
       ,pjtname varchar2(40) not null
       ,middle varchar2(4)
       ,dstid number(10)
       ,ctyid number(10)
       ,start_date date
       ,comm varchar2(2000)
       ,primary key (pjtid)
       ,foreign key (ctyid) references county_info(ctyid)
       ,foreign key (dstid) references district_info(dstid)
);


/** ����������� */
create sequence cmt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table community_info (
       cmtid number(10)
       ,cmtname varchar2(20) unique
       ,briefname varchar2(10)
       ,cmtaddress varchar2(200)
       ,gis varchar2(2000)
       ,picaddress varchar2(100)
       ,comm varchar2(2000)
       ,primary key (cmtid)
);


/** ��Դ������վ��������� */
create sequence src_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/** ���Ȱ��� ���� */
create table heatsource_info(
       srcid number(10)
       ,srcname varchar2(20) not null
       ,dstid number(10)
       ,srcaddress varchar2(200)
       ,heattype varchar2(20)
       ,comm varchar2(2000)
       ,primary key (srcid)
       ,foreign key (dstid) references district_info(dstid)
);


/** ���������� */
create sequence mch_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/** ���� ȱ��**/
create table machineset_info(
       mchid number(10)
       ,mchname varchar2(20) not null
       ,srcid number(10)
       ,gis varchar2(2000)
       ,primary key (mchid)
       ,foreign key (srcid) references heatsource_info(srcid)
);


/** ¥��������� */
create sequence bld_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table building_info (
       bldid number(10)
       ,cmtname varchar2(20)
       ,bldname varchar2(20)
       ,bldaddress varchar2(200)
       ,srcid number(10)
       ,heattype varchar2(10)
       ,gis varchar2(2000)
       ,picaddress varchar2(100)
       ,comm varchar2(2000)
       ,primary key (bldid)
       ,unique (cmtname,bldname)
       ,foreign key (cmtname) references community_info(cmtname)
       ,foreign key (srcid) references heatsource_info(srcid)
);



/** ��Ԫ������� */
create sequence unt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table unit_info (
       untid number(10)
       ,cmtname varchar2(20)
       ,bldname varchar2(20)
       ,untname varchar2(20)
       ,mchid number(10)
       ,gis varchar2(2000)
       ,picaddress varchar2(100)
       ,comm varchar2(2000)
       ,primary key (untid)
       ,unique (cmtname,bldname,untname)
       ,foreign key (cmtname,bldname) references building_info(cmtname,bldname)
       ,foreign key (mchid) references machineset_info(mchid)
);

