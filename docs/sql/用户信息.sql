/* 用户信息 */
create sequence usr_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
       
       /* 保证这里社区，楼栋，单元的组合要在unit_info表里提前有记录*/
create table users_info(
       usrid number(10)
       ,usertype varchar2(10)
       ,pjtid number(10)
       ,cmtid number(10) not null
       ,bldid number(10) not null
       ,untid number(10) not null
       ,mchid number(10)
       ,address varchar2(200)
       ,usrname varchar2(20)
       ,phone varchar2(20)
       ,startdate date
       ,contractdate date
       ,contracttype varchar2(50)
       ,contractver varchar2(10)
       ,contractpic varchar2(200)
       ,idpic varchar2(200)
       ,houseidpic varchar2(200)
       ,housepic varchar2(200)
       ,comm varchar2(2000)
       ,primary key (usrid)
       ,foreign key (pjtid) references project_info(pjtid)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (untid) references unit_info(untid)
       ,foreign key (mchid) references machineset_info(mchid)
);


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
       ,area number(8,2)  --建筑面积
       ,realarea number(8,2) --套内面积
       ,feearea number(8,2)  --计费面积
       ,feetype varchar2(10) --计费方式
       ,feerate number(8,2)  --费率
       ,discount number(8,2)  --折扣
       ,reducefee number(10,2)  --减免额
       ,heatstate varchar2(10)  --供热状态
       ,feerate number(8,2)  --热计量基数
       ,housetype varchar2(20)  --户型
       ,comm varchar2(2000)
       ,primary key (feeid)
       ,foreign key (usrid) references users_info(usrid)
);



/* 用户日志*/
create sequence log_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table user_log(
       logid number(10)
       ,usrid number(10) not null
       ,logtype varchar2(20)
       ,logtitle varchar2(20)
       ,logcontent varchar2(10000)
       ,primary key (logid)
       ,foreign key (usrid) references users_info(usrid)
);


/* 账户信息 ,账号内部使用？*/

/* 这个表应该是一个账期一张？表名要加日期合适*/
create table account_info(
       accid varchar2(20)   --内部使用，那就自动生成？
       ,usrid number(10) not null unique
       ,lastbalance number(10,2) --上期余额
       ,curbalance number(10,2)  --本期余额
       ,curcharge number(10,2)  --本期应缴
       ,curmoney number(10,2)  --本期实缴
       ,lastdate date --最后一次缴费时间
       ,--账期是什么格式？
       
       ,primary key (accid)
       ,foreign key (usrid) references users_info(usrid)       
);



/*应缴记录*/

create sequence charge_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table charge_info(
       chgid number(10)
       ,accid number(10)
       ,cyear number(4) --表应拆分
       ,chgtype varchar2(10)  --缴费类型
       ,duearea number(8,2)   --应缴面积
       ,feerate number(8,2)  --费率？
       ,duefee number(8,2)  --应缴金额
       ,money number(8,2) --实缴金额
       ,lastdate date --本期最后一次缴费时间
       ,primary key (chgid)
       ,foreign key (accid) references account_info(accid)
);


/* 缴费流水记录*/

create sequence record_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table charge_record(
       rcdid number(10)
       ,accid number(10)
       ,rcdtime date
       ,money number(8,2)
       ,chgtype varchar2(20) --缴费方式
       ,checknum varchar2(20)  --支票号/POS流水号/转账单流水号
       ,rcdpic varchar2(100)
       ,chgyear varchar2(20)  --缴费所属账期，账期格式？
       ,chgid number(10)
       ,chargerid number(10),
       ,financechecker number(10)
       ,bankrcdid number()   --
       ,primary key (rcdid)
       ,foreign key (accid) references account_info(accid)
       ,foreign key (chgid) references charge_info(chgid)      
);



