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

drop sequence opr_id;
drop sequence oprgroup_id;
drop sequence oomapping_id;
drop sequence sonmapping_id;
drop sequence somapping_id;
drop sequence shouldcharge_id;

drop table unit_info;
drop table building_info;
drop table community_info;
drop table certificate_charge_mapping;
drop table charge_record;
drop table accountrange_info;
drop table user_log;
drop table fee_info;

drop table shouldcharge;

drop table users_info;
drop table machineset_info;
drop table heatsource_info;

drop table staff_oprgroup_pjt_mapping_info;
drop table staff_oprgroup_nopjt_mapping_info;

drop table project_info;
drop table district_info;
drop table county_info;
drop table bank_certificate;
drop table bank_info;
drop table course_info;
drop table login_info;
drop table staff_info;


drop table opr_operationgroup_mapping_info;
drop table operation_info;
drop table operationgroup_info;


/*系统配置表*/
create sequence cfg_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table config(
       cfgid number(10)
       ,dealname varchar2(200)
       ,startdate date
       ,enddate date
       ,primary key (cfgid)
);



/*班组信息*/
create sequence cls_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table class_info (
       clsid number(10)
       ,clsname varchar2(200)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,primary key (clsid)
);

/*员工信息*/
create sequence staff_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table staff_info(
       stfid number(10)
       ,stfname varchar2(20)
       ,stfnumber varchar2(20) unique
       ,startdate date
       ,phone varchar2(20)
       ,loginname varchar2(20)
       ,pwd varchar2(20)
	     ,department varchar2(20)
	     ,verifytype number(10)
       ,isvalid number(1) default(1) --1代表合法
       ,primary key (stfid)
);

/*员工班组映射*/
create sequence cls_stf_map_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table cls_stf_map(
       mapid number(10)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,clsid number(10)
       ,stfid number(10)
       ,primary key (mapid)
       ,unique (clsid,stfid)
       ,foreign key (clsid) references class_info(clsid)
       ,foreign key (stfid) references staff_info(stfid) 
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
       ,townname varchar2(200) not null
       ,cityname varchar2(200) not null
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
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
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
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
       ,pjtnum varchar2(20) not null
       ,pjtname varchar2(200) not null
       ,middle varchar2(4)
       ,departmentname varchar2(20) 
       ,start_date date
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,dstid number(10)
       ,ctyid number(10)
       ,primary key (pjtid)
       ,foreign key (ctyid) references county_info(ctyid)
       ,foreign key (dstid) references district_info(dstid)
);

/*项目和员工映射*/
create sequence pjt_stf_map_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
    
create table pjt_stf_map(
       mapid number(10)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,pjtid number(10)
       ,stfid number(10)
       ,unique (pjtid,stfid)
       ,primary key (mapid)
       ,foreign key (pjtid) references project_info(pjtid)
       ,foreign key (stfid) references staff_info(stfid)
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
       ,cmtname varchar2(200) unique
       ,briefname varchar2(100)
       ,cmtaddress varchar2(200)
       ,gis varchar2(2000)
       ,picaddress varchar2(200)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,primary key (cmtid)
);

/* 热源（换热站）表及其相关 */
create sequence src_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table heatsource_info(
       srcid number(10)
       ,srcname varchar2(200) not null  
       ,address varchar2(200)
       ,heattype varchar2(20)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,dstid number(10)
       ,primary key (srcid)
       ,foreign key (dstid) references district_info(dstid)
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
       ,mchname varchar2(200) not null    
       ,gis varchar2(2000)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,srcid number(10)
       ,clsid number(10)
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
       ,bldname varchar2(200)
       ,address varchar2(200)
       ,heattype varchar2(10)
       ,gis varchar2(2000)
       ,picaddress varchar2(200)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,cmtid number(10)
       ,mchid number(10)
       ,primary key (bldid)
       ,unique (cmtid,bldname)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (mchid) references machineset_info(mchid)
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
       ,untname varchar2(200)
       ,gis varchar2(2000)
       ,picaddress varchar2(200)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,cmtid number(10)
       ,bldid number(10)
       ,primary key (untid)
       ,unique (cmtid,bldid,untname)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)   
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
       ,address varchar2(200)
       ,usrname varchar2(200)
       ,phone varchar2(20)
       ,startdate date
       ,contractdate date
       ,contracttype varchar2(50)
       ,contractver varchar2(10)
       ,contractpic varchar2(200)
       ,idpic varchar2(200)
       ,houseidpic varchar2(200)
       ,housepic varchar2(200)
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
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,pjtid number(10)
       ,cmtid number(10)
       ,bldid number(10)
       ,untid number(10)
       ,mchid number(10)
       ,primary key (usrid)
       ,foreign key (pjtid) references project_info(pjtid)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (untid) references unit_info(untid)
       ,foreign key (mchid) references machineset_info(mchid)
);

/
/* 计费信息 */
/*
create sequence fee_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table fee_info(
       feeid number(10)  
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
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,usrid number(10) not null
       ,primary key (feeid)
       ,foreign key (usrid) references users_info(usrid)
);

*/

/* 用户日志*/
create sequence log_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table user_log(
       logid number(15)  
       ,logtype varchar2(20)
       ,logtitle varchar2(20)
       ,logcontent varchar2(2000)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,usrid number(10) not null
       ,primary key (logid)
       ,foreign key (usrid) references users_info(usrid)
);

/* 账期信息 */
create sequence deal_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table deal_info(
       dealid number(15)   --内部使用，自动生成
       ,balance number(10,2)  --本期余额
       ,money number(10,2)  --本期实缴（本期已缴）
       ,lastdate date --最后一次缴费时间
       ,dealname varchar2(200) not null  --账期名称
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,usrid number(10) not null
       ,unique (usrid,dealname)
       ,primary key (dealid)
       ,foreign key (usrid) references users_info(usrid)       
);



/* 应缴记录 */
create sequence duecharge_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table due_charge(
        chgid number(15)
        ,charge number(8,2)  --本期应缴
		    ,dealname varchar2(200) not null --账期名称
        ,chgtype varchar2(20)   --应缴类型（欠费等）
        ,area number(8,2)  --缴费面积
		    ,rate number(8,2)  --缴费费率
        ,reducechg number(8,2) --金额减免
		    ,money number(8,2)  --本期实缴
		    ,lastchgtime date
        ,isvalid number(1) default(1) --1代表合法
        ,usrid number(10) not null
		    ,primary key (chgid)
		    ,foreign key (usrid) references users_info(usrid)   
);

/*应缴记录与流水的映射关系见后面*/



/*科目信息*/
create sequence crs_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table course_info(
       crsid number(10)
       ,crsnum varchar2(200) unique
       ,crsname varchar2(200)
       ,desp varchar2(2000)
	     ,dealname varchar2(200)
       ,isvalid number(1) default(1) --1代表合法
       ,primary key (crsid)
);

/* 银行信息*/
create sequence bnk_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table bank_info(
       bnkid number(10)
       ,bnknum varchar2(20) not null
       ,bnkname varchar2(20) not null
       ,accountnum varchar2(20)
       ,desp varchar2(2000)
       ,isvalid number(1) default(1) --1代表合法
       ,crsid number(10) not null
       ,primary key (bnkid)
       ,unique (bnknum,accountnum,crsid)
       ,foreign key (crsid) references course_info(crsid)
);

/*银行凭证记录*/
create sequence certificate_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table bank_certificate(
       ctfid number(15)   
       ,ctftype varchar2(10)
       ,ctfnumber varchar2(20)
       ,money number(10,2) 
       ,undertaker varchar2(20)
       ,ctfdate date
       ,importdate date
       ,importer varchar2(20)
       ,relatednum number(10)
       ,isvalid number(1) default(1) --1代表合法
       ,bnkid number(10)
       ,primary key (ctfid)
       ,foreign key (bnkid) references bank_info(bnkid)
);


/* 缴费流水记录*/
create sequence record_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table charge_record(
       rcdid number(15)       
       ,rcdtime date
       ,money number(8,2)
       ,chgtype varchar2(20) --缴费方式
       ,checknum varchar2(20)  --支票号/POS流水号/转账单流水号
       ,rcdpic varchar2(200)
       ,dealname varchar2(200)  --账期名称 
       ,financechecker number(10) 
       ,desp varchar2(2000)
	     ,chargeverifytime date
       ,isvalid number(1) default(1) --1代表合法
       ,usrid number(10) not null
       ,stfid number(10) not null
       ,ctfid number(10) 
       ,primary key (rcdid)   
       ,foreign key (usrid) references users_info(usrid)   
	     ,foreign key (stfid) references staff_info(stfid)
       ,foreign key (ctfid) references bank_certificate(ctfid)  	 
);


/* 应缴记录流水映射*/
create sequence chg_rcd_mapping_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;

create table due_charge_record_mapping(
       mapid number(15)	   
       ,isvalid number(1) default(1) --1代表合法
       ,chgid number(10)
		   ,rcdid number(10)
		   ,primary key (mapid)
		   ,foreign key (chgid) references due_charge(chgid)
		   ,foreign key (rcdid) references charge_record(rcdid)
);

/* 用户登陆记录*/
create sequence login_id
       maxvalue 9999999999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table login_info(
       lginid number(15)
       ,logindate date
       ,ip varchar2(20)
       ,success varchar2(4)
       ,failrsn varchar2(2000) --登陆失败原因
       ,oltime varchar2(20)  --在线时长
       ,isvalid number(1) default(1) --1代表合法
       ,stfid number(10)
       ,primary key (lginid)
       ,foreign key (stfid) references staff_info(stfid)
);





/***********************下一阶段***********************/

/* 操作描述表*/
create sequence opr_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table operation_info(
       oprid number(10)
       ,oprname varchar2(20)
       ,primary key (oprid)
);

/* 权限组表*/
create sequence oprgroup_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table operationgroup_info(
       oprgroupid number(10)
       ,groupname varchar2(20)
	   ,grouptype number(10)
       ,primary key (oprgroupid)
);

/* 权限映射表*/
create sequence oomapping_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table opr_operationgroup_mapping_info(
       oomappingid number(10)
       ,oprgroupid number(10)
	   ,oprid number(10)
       ,primary key (oomappingid)
	   ,foreign key (oprgroupid) references operationgroup_info(oprgroupid)
	   ,foreign key (opr_id) references operation_info(opr_id)
);

/* 权限映射项目无关表*/
create sequence sonmapping_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table staff_oprgroup_nopjt_mapping_info(
       sonmappingid number(10)
       ,staffid number(10)
	   ,oprgroupid number(10)
       ,primary key (sonmappingid)
	   ,foreign key (oprgroupid) references operationgroup_info(oprgroupid)
	   ,foreign key (staffid) references staff_info(staffid)
);

/* 权限映射项目相关表*/
create sequence somapping_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table staff_oprgroup_pjt_mapping_info(
       somappingid number(10)
       ,staffid number(10)
       ,oprgroupid number(10)
	     ,pjtid number(10)
       ,primary key (sonmappingid)
	     ,foreign key (oprgroupid) references operationgroup_info(oprgroupid)
	     ,foreign key (staffid) references staff_info(staffid)
	     ,foreign key (pjtid) references project_info(pjtid)
);
