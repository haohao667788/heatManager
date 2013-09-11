drop sequence cty_id;
drop sequence dst_id;
drop sequence pjt_id;
drop sequence cmt_id; 
drop sequence bld_id; 
drop sequence unt_id; 
drop sequence mch_id; 
drop sequence src_id; 
drop sequence usr_id;
drop sequence fee_id;
drop sequence log_id;
drop sequence accrange_id;
drop sequence record_id;
drop sequence ccmapping_id;
drop sequence certificate_id;
drop sequence login_id;

drop table unit_info;
drop table building_info;
drop table community_info;
drop table certificate_charge_mapping;
drop table charge_record;
drop table accountrange_info;
drop table user_log;
drop table fee_info;
drop table users_info;
drop table machineset_info;
drop table heatsource_info;
drop table project_info;
drop table district_info;
drop table county_info;
drop table bank_certificate;
drop table bank_info;
drop table course_info;
drop table login_info;
drop table staff_info;



/*供热班组及其相关*/
create sequence cls_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table class_info (
       clsid number(10)
       ,clsname varchar2(20)
       ,comm varchar2(2000)
       ,primary key (clsid)
);


/* 区县表及其相关 */
create sequence cty_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table county_info (
       ctyid number(10)
       ,townname varchar2(20) not null
       ,cityname varchar2(20) not null
       ,comm varchar2(2000)
       ,primary key (ctyid)
       ,unique (townname,cityname)
);       

/* 大区表及其相关 */
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

/* 项目表及其相关 */
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

/* 社区表及其相关 */
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

/* 热源（换热站）表及其相关 */
create sequence src_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/* 供热班组 已加*/
create table heatsource_info(
       srcid number(10)
       ,srcname varchar2(20) not null
       ,dstid number(10)
       ,clsid number(10)
       ,srcaddress varchar2(200)
       ,heattype varchar2(20)
       ,comm varchar2(2000)
       ,primary key (srcid)
       ,foreign key (dstid) references district_info(dstid)
       ,foreign key (clsid) references class_info(clsid)
);

/* 机组表及其相关 */
create sequence mch_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table machineset_info(
       mchid number(10)
       ,mchname varchar2(20) not null
       ,srcid number(10)
       ,clsid number(10)
       ,gis varchar2(2000)
       ,primary key (mchid)
       ,foreign key (srcid) references heatsource_info(srcid)
       ,foreign key (clsid) references class_info(clsid)
);

/* 楼栋表及其相关 */
create sequence bld_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table building_info (
       bldid number(10)
       ,cmtid number(10)
       ,bldname varchar2(20)
       ,bldaddress varchar2(200)
       ,srcid number(10)
       ,heattype varchar2(10)
       ,gis varchar2(2000)
       ,picaddress varchar2(100)
       ,comm varchar2(2000)
       ,primary key (bldid)
       ,unique (cmtid,bldname)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (srcid) references heatsource_info(srcid)
);

/* 单元表及其相关 */
create sequence unt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table unit_info (
       untid number(10)
       ,cmtid number(10)
       ,bldid number(10)
       ,untname varchar2(20)
       ,mchid number(10)
       ,gis varchar2(2000)
       ,picaddress varchar2(100)
       ,comm varchar2(2000)
       ,primary key (untid)
       ,unique (cmtid,bldid,untname)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (mchid) references machineset_info(mchid)
);

/* 用户信息 */
create sequence usr_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
     
create table users_info(
       usrid number(10)
       ,usertype varchar2(10)
       ,pjtid number(10)
       ,cmtid number(10)
       ,bldid number(10)
       ,untid number(10)
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
       ,heatbase number(8,2)  --热计量基数
       ,heatrate number(8,2)  --热计量费率
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
       ,logcontent varchar2(2000)
       ,primary key (logid)
       ,foreign key (usrid) references users_info(usrid)
);

/* 账期信息 */
create sequence accrange_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table accountrange_info(
       accrangeid varchar2(20)   --内部使用，自动生成
       ,usrid number(10) not null
       ,curbalance number(10,2)  --本期余额
       ,curcharge number(10,2)  --本期应缴
       ,curmoney number(10,2)  --本期实缴
       ,lastdate date --最后一次缴费时间
       ,finacerange varchar2(20)
       ,donefinacerange varchar2(20)
       ,unique (usrid,finacerange)
       ,primary key (accrangeid)
       ,foreign key (usrid) references users_info(usrid)       
);

/* 缴费流水记录*/
create sequence record_id
       maxvalue 9999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table charge_record(
       rcdid number(10)
       ,accrangeid number(10)
       ,usrid number(10) not null
       ,rcdtime date
       ,money number(8,2)
       ,chgtype varchar2(20) --缴费方式
       ,checknum varchar2(20)  --支票号/POS流水号/转账单流水号
       ,rcdpic varchar2(100)
       ,chgyear varchar2(20)  --缴费所属账期，账期格式？
       ,chargerid number(10)
       ,financechecker number(10)
       ,cid number(10)
       ,comm varchar2(2000)
       ,primary key (rcdid)
       ,foreign key (usrid) references users_info(usrid)       
);

/*科目信息*/
create table course_info(
       crsid number(10)
       ,crsname varchar2(20)
       ,desp varchar2(2000)
       ,comm varchar2(2000)
       ,primary key (crsid)
);

/* 银行信息*/
create table bank_info(
       bnkid number(10)
       ,bnkname varchar2(20) not null
       ,crsid number(10)
       ,primary key (bnkid)
       ,foreign key (crsid) references course_info(crsid)
);

/*银行凭证记录*/
create sequence certificate_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table bank_certificate(
       ctfid number(10)
       ,bnkid number(10)
       ,ctftype varchar2(10)
       ,ctfnumber varchar2(20)
       ,money number(10,2) 
       ,undertaker varchar2(20)
       ,cdate date
       ,importdate date
       ,importer varchar2(20)
       ,relatednum number(10)
       ,primary key (ctfid)
       ,foreign key (bnkid) references bank_info(bnkid)
);

create sequence ccmapping_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table certificate_charge_mapping(
       ccmappingid number(10)
       ,ctfid number(10)
       ,rcdid number(10)
       ,primary key (ccmappingid)
       ,foreign key (ctfid) references bank_certificate(ctfid)
       ,foreign key (rcdid) references charge_record(rcdid)
);

/*用户员工信息*/
create table staff_info(
       stfnumber varchar2(20)
       ,stfname varchar2(20)
       ,startdate date
       ,contactnumber varchar2(20)
       ,loginname varchar2(20)
       ,pwd varchar2(20)
       ,primary key (stfnumber)
);

/* 用户登陆记录*/
create sequence login_id
       maxvalue 9999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table login_info(
       lginid number(10)
       ,stfnumber varchar2(20)
       ,logindate date
       ,ip varchar2(20)
       ,success varchar2(4)
       ,failrsn varchar2(2000) --登陆失败原因
       ,oltime varchar2(20)  --在线时长
       ,primary key (lginid)
       ,foreign key (stfnumber) references staff_info(stfnumber)
);

