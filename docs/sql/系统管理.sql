/*用户员工信息*/

create sequence staff_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table staff_info(
       stfid number(10)
       ,stfname varchar2(20)
       ,startdate date
       ,--工号为啥不做主键？
       
)；



/* 用户登陆记录*/


create sequence login_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table login_info(
       lginid number(10)
       ,stfid number(10)
       ,logindate date
       ,ip varchar2(20)
       ,success varchar2(4)
       ,failrsn varchar2(2000) --登陆失败原因
       ,oltime varchar2(20)  --在线时长
       ,primary key (lginid)
       ,foreign key (stfid) references staff_info(stfid)
);

create or replace trigger login_trigger 
before insert on login_info 
for each row 
  begin 
    select login_id.nextval into :new.lginid from dual;
  end;
/
