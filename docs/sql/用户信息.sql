/* �û���Ϣ */
create sequence usr_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
       
       /* ��֤����������¥������Ԫ�����Ҫ��unit_info������ǰ�м�¼*/
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


/* �Ʒ���Ϣ */
create sequence fee_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;

/* �ȼ������� ����*/
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
       ,feerate number(8,2)  --�ȼ�������
       ,housetype varchar2(20)  --����
       ,comm varchar2(2000)
       ,primary key (feeid)
       ,foreign key (usrid) references users_info(usrid)
);



/* �û���־*/
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


/* �˻���Ϣ ,�˺��ڲ�ʹ�ã�*/

/* �����Ӧ����һ������һ�ţ�����Ҫ�����ں���*/
create table account_info(
       accid varchar2(20)   --�ڲ�ʹ�ã��Ǿ��Զ����ɣ�
       ,usrid number(10) not null unique
       ,lastbalance number(10,2) --�������
       ,curbalance number(10,2)  --�������
       ,curcharge number(10,2)  --����Ӧ��
       ,curmoney number(10,2)  --����ʵ��
       ,lastdate date --���һ�νɷ�ʱ��
       ,--������ʲô��ʽ��
       
       ,primary key (accid)
       ,foreign key (usrid) references users_info(usrid)       
);



/*Ӧ�ɼ�¼*/

create sequence charge_id
       maxvalue 9999999999
       increment by 1
       start with 1
       nocycle
       nocache;
       
create table charge_info(
       chgid number(10)
       ,accid number(10)
       ,cyear number(4) --��Ӧ���
       ,chgtype varchar2(10)  --�ɷ�����
       ,duearea number(8,2)   --Ӧ�����
       ,feerate number(8,2)  --���ʣ�
       ,duefee number(8,2)  --Ӧ�ɽ��
       ,money number(8,2) --ʵ�ɽ��
       ,lastdate date --�������һ�νɷ�ʱ��
       ,primary key (chgid)
       ,foreign key (accid) references account_info(accid)
);


/* �ɷ���ˮ��¼*/

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
       ,chgtype varchar2(20) --�ɷѷ�ʽ
       ,checknum varchar2(20)  --֧Ʊ��/POS��ˮ��/ת�˵���ˮ��
       ,rcdpic varchar2(100)
       ,chgyear varchar2(20)  --�ɷ��������ڣ����ڸ�ʽ��
       ,chgid number(10)
       ,chargerid number(10),
       ,financechecker number(10)
       ,bankrcdid number()   --
       ,primary key (rcdid)
       ,foreign key (accid) references account_info(accid)
       ,foreign key (chgid) references charge_info(chgid)      
);



