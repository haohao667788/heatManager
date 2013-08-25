/* 银行信息*/

create table bank_info(
       bnkid number(10)
       ,bnkname varchar2(20) not null
       ,accid number(10) not null  --用户账号？
       ,crsid number(10)
       ,primary key (bnkid)
       ,foreign key (crsid) references course_info(crsid)
       ,foreign key (accid) references account_info(accid)
);


/*科目信息*/

create table course_info(
       crsid number(10)
       ,chgyear varchar2(10) --年度单位暂定为String
       ,--科目号？科目编号啥区别？
       ,crsname varchar2(20)
       ,desp varchar2(2000)
       ,comm varchar2(2000)
       primary key (crsid)
);



/*费用扣除项目*/



/*银行凭证记录*/

create table bank_record(
       
)

create table 

