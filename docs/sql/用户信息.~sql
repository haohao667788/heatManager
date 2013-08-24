/* 用户信息 */
create sequence usr_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table users_info(
       usrid number(10)
       ,ctyid number(10) not null
       ,dstid number(10) not null
       ,pjtid number(10) not null
       ,cmtid number(10) not null
       ,bldid number(10) not null
       ,untid number(10) not null
       ,mchid number(10) not null
       ,address varchar2(200) not null
       ,usrname varchar2(20) not null
       ,contactnum varchar2(20) not null
       ,startdate date not null
       ,startpicaddrs varchar2(20) not null
       ,idpic varchar2(20) not null
       ,houseidpic varchar2(20) not null
       ,housepic varchar2(20)  --此处可空
       ,comm varchar2(2000)
       ,primary key (usrid)
       ,foreign key (ctyid) references county_info(ctyid)
       ,foreign key (dstid) references district_info(dstid)
       ,foreign key (pjtid) references project_info(pjtid)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (untid) references unit_info(untid)
       ,foreign key (mchid) references machineset_info(mchid)
);

create or replace trigger usr_trigger 
before insert on users_info 
for each row 
  begin 
    select usr_id.nextval into :new.usrid from dual;
  end;
/

/* 计费信息 */
create sequence fee_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/* 热计量基数 待加*/
create table fee_info(
       feeid number(10)
       ,usrid number(10) not null
       ,area number(8,2) not null
       ,realarea number(8,2) not null
       ,feearea number(8,2) not null
       ,feetype varchar2(10) not null
       ,feerate number(8,2) not null
       ,discount number(8,2) not null
       ,reducefee number(10,2) not null
       ,heatstate varchar2(10) not null
       ,feerate number(8,2) not null
       ,housetype varchar2(20) not null
       ,desp varchar2(200) not null
       ,comm varchar2(2000) not null
       ,primary key (feeid)
       ,foreign key (usrid) references users_info(usrid)
);

create or replace trigger fee_trigger 
before insert on fee_info 
for each row 
  begin 
    select fee_id.nextval into :new.feeid from dual;
  end;
/

/* 账户信息 */

