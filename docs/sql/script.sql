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



/*���Ȱ��鼰�����*/
create sequence cls_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table class_info (
       clsid number(10)
       ,clsname varchar2(20)
       ,desp varchar2(2000)
       ,primary key (clsid)
);

/* ���ر������ */
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
       ,desp varchar2(2000)
       ,primary key (ctyid)
       ,unique (townname,cityname)
);       

/* ����������� */
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
       ,primary key (dstid)
);

/* ��Ŀ������� */
create sequence pjt_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table project_info (
       pjtid number(10)
       ,pjtnum varchar2(20) not null
       ,pjtname varchar2(40) not null
       ,middle varchar2(4)
       ,departmentname varchar2(20)
       ,dstid number(10)
       ,ctyid number(10)
       ,start_date date
       ,desp varchar2(2000)
       ,primary key (pjtid)
       ,foreign key (ctyid) references county_info(ctyid)
       ,foreign key (dstid) references district_info(dstid)
);

/* ����������� */
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
       ,desp varchar2(2000)
       ,primary key (cmtid)
);

/* ��Դ������վ��������� */
create sequence src_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table heatsource_info(
       srcid number(10)
       ,srcname varchar2(20) not null
       ,dstid number(10)
       ,clsid number(10)
       ,srcaddress varchar2(200)
       ,heattype varchar2(20)
       ,desp varchar2(2000)
       ,primary key (srcid)
       ,foreign key (dstid) references district_info(dstid)
       ,foreign key (clsid) references class_info(clsid)
);

/* ���������� */
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
       ,desp varchar2(2000)
       ,primary key (mchid)
       ,foreign key (srcid) references heatsource_info(srcid)
       ,foreign key (clsid) references class_info(clsid)
);

/* ¥��������� */
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
       ,desp varchar2(2000)
       ,primary key (bldid)
       ,unique (cmtid,bldname)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (srcid) references heatsource_info(srcid)
);

/* ��Ԫ������� */
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
       ,desp varchar2(2000)
       ,primary key (untid)
       ,unique (cmtid,bldid,untname)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (mchid) references machineset_info(mchid)
);

/* �û���Ϣ */
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
       ,desp varchar2(2000)
       ,primary key (usrid)
       ,foreign key (pjtid) references project_info(pjtid)
       ,foreign key (cmtid) references community_info(cmtid)
       ,foreign key (bldid) references building_info(bldid)
       ,foreign key (untid) references unit_info(untid)
       ,foreign key (mchid) references machineset_info(mchid)
);

/* �Ʒ���Ϣ */
create sequence fee_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table fee_info(
       feeid number(10)
       ,usrid number(10) not null
       ,area number(8,2)  --�������
       ,realarea number(8,2) --�������
       ,feearea number(8,2)  --�Ʒ����
       ,feetype varchar2(10) --�Ʒѷ�ʽ
       ,feerate number(8,2)  --����
       ,discount number(8,2)  --�ۿ�
       ,reducefee number(10,2)  --�����
       ,heatstate varchar2(10)  --����״̬
       ,heatbase number(8,2)  --�ȼ�������
       ,heatrate number(8,2)  --�ȼ�������
       ,housetype varchar2(20)  --����
       ,desp varchar2(2000)
       ,primary key (feeid)
       ,foreign key (usrid) references users_info(usrid)
);

/* �û���־*/
create sequence log_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table user_log(
       logid number(15)
       ,usrid number(10) not null
       ,logtype varchar2(20)
       ,logtitle varchar2(20)
       ,logcontent varchar2(2000)
       ,desp varchar2(2000)
       ,primary key (logid)
       ,foreign key (usrid) references users_info(usrid)
);

/* ������Ϣ */
create sequence deal_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table deal_info(
       dealid number(15)   --�ڲ�ʹ�ã��Զ�����
       ,usrid number(10) not null
       ,curbalance number(10,2)  --�������
       ,curcharge number(10,2)  --����Ӧ��
       ,curmoney number(10,2)  --����ʵ��
       ,lastdate date --���һ�νɷ�ʱ��
       ,dealname varchar2(20) not null
       ,desp varchar2(2000)
       ,unique (usrid,dealname)
       ,primary key (dealid)
       ,foreign key (usrid) references users_info(usrid)       
);

/*����������Ϣ*/
create sequence deal_gen
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table deal_generator(
       genid number(10)
       ,dealname varchar2(20) not null
       ,dealdate date not null
       ,primary key (genid)
);




/* Ӧ�ɼ�¼ */
create sequence duecharge_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;

create table due_charge(
        chgid number(15)
        ,usrid number(10) not null
		    ,dealname varchar2(20) not null 
        ,dealmonth varchar2(20) not null
        ,chgtype varchar2(20)
		    ,chgnumber number(8,2)
		    ,realchgnumber number(8,2)
		    ,lastchgtime date
		    ,primary key (chgid)
		    ,foreign key (usrid) references users_info(usrid)   
);


/*��Ŀ��Ϣ*/
create table course_info(
       crsid number(10)
       ,crsname varchar2(20)
       ,desp varchar2(2000)
	     ,chgyear varchar2(20)
       ,primary key (crsid)
);

/* ������Ϣ*/
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
       ,crsid number(10) not null
       ,desp varchar2(2000)
       ,primary key (bnkid)
       ,unique (bnknum,accountnum,crsid)
       ,foreign key (crsid) references course_info(crsid)
);

/*����ƾ֤��¼*/
create sequence certificate_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       nocycle
       nocache;

create table bank_certificate(
       ctfid number(15)
       ,bnkid number(10)
       ,ctftype varchar2(10)
       ,ctfnumber varchar2(20)
       ,money number(10,2) 
       ,undertaker varchar2(20)
       ,ctfdate date
       ,importdate date
       ,importer varchar2(20)
       ,relatednum number(10)
       ,primary key (ctfid)
       ,foreign key (bnkid) references bank_info(bnkid)
);


/*�û�Ա����Ϣ*/
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
       ,primary key (stfid)
);


/* �ɷ���ˮ��¼*/
create sequence record_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table charge_record(
       rcdid number(15)
       ,accrangeid number(15)
       ,usrid number(10) not null
       ,rcdtime date
       ,money number(8,2)
       ,chgtype varchar2(20) --�ɷѷ�ʽ
       ,checknum varchar2(20)  --֧Ʊ��/POS��ˮ��/ת�˵���ˮ��
       ,rcdpic varchar2(100)
       ,chgyear varchar2(20)  
	     ,stfid number(10)
       ,financechecker number(10)
       ,ctfid number(10)   --
       ,desp varchar2(2000)
	     ,chargeverifytime date
       ,primary key (rcdid)
       ,foreign key (usrid) references users_info(usrid)   
	     ,foreign key (stfid) references staff_info(stfid)
       ,foreign key (ctfid) references  bank_certificate(ctfid)  	 
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

/* Ӧ�ɼ�¼��ˮӳ��*/
create sequence chg_rcd_mapping_id
       maxvalue 999999999999999
       increment by 1
       start with 1
       cycle
       nocache;

create table due_charge_record_mapping(
       mappingid number(15)
		   ,chgid number(10)
		   ,rcdid number(10)
		   ,primary key (mappingid)
		   ,foreign key (chgid) references due_charge(chgid)
		   ,foreign key (rcdid) references charge_record(rcdid)
);

/* �û���½��¼*/
create sequence login_id
       maxvalue 9999999999999999
       increment by 1
       start with 1
       cycle
       nocache;
       
create table login_info(
       lginid number(15)
       ,stfid number(10)
       ,logindate date
       ,ip varchar2(20)
       ,success varchar2(4)
       ,failrsn varchar2(2000) --��½ʧ��ԭ��
       ,oltime varchar2(20)  --����ʱ��
       ,primary key (lginid)
       ,foreign key (stfid) references staff_info(stfid)
);

/* ����������*/
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

/* Ȩ�����*/
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

/* Ȩ��ӳ���*/
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

/* Ȩ��ӳ����Ŀ�޹ر�*/
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

/* Ȩ��ӳ����Ŀ��ر�*/
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
