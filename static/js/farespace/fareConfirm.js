/**
 * 流水tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.fareConfirm");

Heat.fareConfirm.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.fareConfirm.BasicForm.superclass.constructor.call(this, {
            url: '/static/data/farespace/fareConfirm/update'+debug,
            width: 600,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,

            items: [{
                xtype: 'hidden',
                name: 'usrid'
            }, {
                layout: 'column',
                items: [{
                    columnWidth:.5,
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        fieldLabel: '户主姓名',
                        name: 'usrname',
                        width: 160,
                        allowBlank: false
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '流水类型',
                        name: 'usrtype',
                        width: 160,
                        allowBlank: false
                    }, new Ext.form.ComboBox({
                        hiddenName: 'cmtid',
                        mode: 'local',
                        width: 160,
                        fieldLabel: '所在社区',
                        triggerAction: 'all',
                        valueField: 'value',
                        displayField: 'text',
                        allowBlank: false,
                        editable: false,
                        store: new Ext.data.Store({
                            autoLoad: true,
                            proxy: new Ext.data.HttpProxy({url: "/static/data/farespace/fareConfirm/queryShequ"+debug}),
                            reader: new Ext.data.ArrayReader({}, [
                                {name: 'value'},
                                {name: 'text'}
                            ])
                        })
                    }), new Ext.form.ComboBox({
                        hiddenName: 'bldid',
                        mode: 'local',
                        width: 160,
                        fieldLabel: '所在楼栋',
                        triggerAction: 'all',
                        valueField: 'value',
                        displayField: 'text',
                        allowBlank: false,
                        editable: false,
                        store: new Ext.data.Store({
                            autoLoad: true,
                            proxy: new Ext.data.HttpProxy({url: "/static/data/farespace/fareConfirm/queryLoudong"+debug}),
                            reader: new Ext.data.ArrayReader({}, [
                                {name: 'value'},
                                {name: 'text'}
                            ])
                        })
                    }), new Ext.form.ComboBox({
                        hiddenName: 'untid',
                        mode: 'local',
                        width: 160,
                        fieldLabel: '所在单元',
                        triggerAction: 'all',
                        valueField: 'value',
                        displayField: 'text',
                        allowBlank: false,
                        editable: false,
                        store: new Ext.data.Store({
                            autoLoad: true,
                            proxy: new Ext.data.HttpProxy({url: "/static/data/farespace/fareConfirm/queryDanyuan"+debug}),
                            reader: new Ext.data.ArrayReader({}, [
                                {name: 'value'},
                                {name: 'text'}
                            ])
                        })
                    }), {
                        xtype: 'textfield',
                        fieldLabel: '流水地址',
                        name: 'usraddress',
                        width: 160,
                        allowBlank: false
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '联系方式',
                        name: 'communicate',
                        width: 160,
                        allowBlank: false
                    }, new Ext.form.ComboBox({
                        hiddenName: 'mchid',
                        mode: 'local',
                        width: 160,
                        fieldLabel: '所属机组',
                        triggerAction: 'all',
                        valueField: 'value',
                        displayField: 'text',
                        allowBlank: false,
                        editable: false,
                        store: new Ext.data.Store({
                            autoLoad: true,
                            proxy: new Ext.data.HttpProxy({url: "/static/data/farespace/fareConfirm/queryUnit"+debug}),
                            reader: new Ext.data.ArrayReader({}, [
                                {name: 'value'},
                                {name: 'text'}
                            ])
                        })
                    })]
                }, {
                    columnWidth:.5,
                    layout: 'form',
                    items: [{
                        xtype: 'datefield',
                        fieldLabel: '开户时间',
                        editable: false,
                        format: 'Y-m-d',
                        name: 'kaihuDate',
                        width: 160
                    }, {
                        xtype: 'datefield',
                        fieldLabel: '合同签订时间',
                        editable: false,
                        format: 'Y-m-d',
                        name: 'contractDate',
                        width: 160
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '合同类型',
                        name: 'contracttype',
                        width: 160,
                        allowBlank: false
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '合同版本',
                        name: 'contractversion',
                        width: 160,
                        allowBlank: false
                    }, {
                        xtype: 'fileuploadfield',
                        fieldLabel: '合同影像',
                        name: 'contractpic',
                        width: 160,
                        buttonText: '',
                        buttonCfg: {
                            iconCls: 'upload_icon'
                        }
                    }, {
                        xtype: 'fileuploadfield',
                        fieldLabel: '身份证影像',
                        name: 'idpic',
                        width: 160,
                        buttonText: '',
                        buttonCfg: {
                            iconCls: 'upload_icon'
                        }
                    }, {
                        xtype: 'fileuploadfield',
                        fieldLabel: '房产证影像',
                        name: 'fangchanpic',
                        width: 160,
                        buttonText: '',
                        buttonCfg: {
                            iconCls: 'upload_icon'
                        }
                    }, {
                        xtype: 'fileuploadfield',
                        fieldLabel: '户型图纸影像',
                        name: 'huxingpic',
                        width: 160,
                        buttonText: '',
                        buttonCfg: {
                            iconCls: 'upload_icon'
                        }
                    }]
                }]
            }]
        });

        this.addEvents('submitcomplete');
    },

    setValues: function(record) {
        this.getForm().loadRecord(record);
    },

    formSubmit: function() {
        this.getForm().submit({
            clientValidation: true,
            waitMsg:'数据保存中...',
            success: this.submitcomplete.createDelegate(this),
            failure: function(form, action) {
                switch (action.failureType) {
                    case Ext.form.Action.CLIENT_INVALID:
                        Ext.Msg.alert('系统提示', '请先填写完所有必填项');
                        break;
                    case Ext.form.Action.CONNECT_FAILURE:
                        Ext.Msg.alert('系统提示', '连接失败，请确认网络连接正常');
                        break;
                    case Ext.form.Action.SERVER_INVALID:
                        Ext.Msg.alert('系统提示', action.result.msg);
                        break;
                }
            }
        });
    },

    reset: function() {
        this.getForm().reset();
    },

    submitcomplete: function(form, action) {
        this.fireEvent('submitcomplete');
    }
});


Heat.fareConfirm.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.fareConfirm.BasicForm();
        Heat.fareConfirm.BasicWin.superclass.constructor.call(this, {
            items: this.form,
            buttons: [{
                text: '提交',
                handler: this.onSubmitClick,
                scope: this
            }, {
                text: '重置',
                handler: this.onResetClick,
                scope: this
            }, {
                text: '取消',
                handler: this.onCancelClick,
                scope: this
            }],

            listeners: {
                'hide': {
                    fn: this.onResetClick,
                    scope: this
                }
            },

            title: '修改记录',
            width: 600,
            buttonAlign: 'center',
            closeAction: 'hide'
        });

        this.addEvents('submitcomplete');
        this.form.on('submitcomplete', this.submitcomplete, this);
    },

    onSubmitClick: function() {
        try {
            this.form.formSubmit();
        }catch(error) {
            Ext.Msg.alert('系统提示', error.message);
            return;
        }
    },

    onResetClick: function() {
        this.form.reset();
    },

    onCancelClick: function() {
        this.winClose();
    },

    load: function(record) {
        this.form.setValues(record);
    },

    winClose: function() {
        //var sButton = this.buttons[0];
        //sButton.enable();
        this.form.reset();
        this.hide();
    },

    submitcomplete: function() {
        this.fireEvent('submitcomplete');
    }
});


Heat.fareConfirm.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    flowWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.flowWin = new Heat.fareConfirm.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/static/data/farespace/fareConfirm/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'flowid', type: 'int'},
                    {name: 'accountid', type: 'int'},
                    {name: 'time', type: 'string'},
                    {name: 'fare', type: 'float'},
                    {name: 'method', type: 'string'},
                    {name: 'methodNo', type: 'int'},
                    {name: 'pic', type: 'string'},
                    {name: 'zhangqi', type: 'string'},
                    {name: 'dueFareNo', type: 'string'},
                    {name: 'farepeople', type: 'string'},
                    {name: 'checkpeople', type: 'string'},
                    {name: 'banknote', type: 'string'},
                    {name: 'checkTime', type: 'string'}
                ]
            })
        });
        Heat.fareConfirm.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "流水号",
                dataIndex: 'flowid',
                width: 80
            }, {
                header: "账号",
                dataIndex: 'accountid',
                width: 100
            }, {
                header: "时间",
                dataIndex: 'time',
                width: 80
            }, {
                header: "金额",
                dataIndex: 'fare',
                width: 80
            }, {
                header: "缴费方式",
                dataIndex: 'method',
                width: 160
            }, {
                header: "支票号码",
                dataIndex: 'methodNo',
                width: 160
            }, {
                header: "缴费所属帐期",
                dataIndex: 'zhangqi',
                width: 160
            }, {
                header: "应缴记录编号",
                dataIndex: 'dueFareNo',
                width: 160
            }, {
                header: "收费员",
                dataIndex: 'farepeople',
                width: 120
            }, {
                header: "财务收款核对员",
                dataIndex: 'checkpeople',
                width: 120
            }, {
                header: "银行凭证号",
                dataIndex: 'banknote',
                width: 100
            }, {
                header: "收费核对确认时间",
                dataIndex: "checkTime",
                width: 160
            }],

//            tbar: [{
//                text: "添加流水",
//                iconCls: "add_icon",
//                handler: this.onAddClick,
//                scope: this
//            }, '-', {
//                text: "修改流水",
//                iconCls: "mod_icon",
//                handler: this.onModClick,
//                scope: this
//            }, '-', {
//                text: "删除流水",
//                iconCls: "del_icon",
//                handler: this.onDelClick,
//                scope: this
//            }],

            bbar: new Ext.PagingToolbar({
                pageSize: 20,
                store: store,
                displayInfo: true,
                displayMsg: '第 {0} - {1} 条，共 {2} 条',
                emptyMsg: '没有记录'
            }),

            viewConfig: {
                forceFit: false
            },

            frame: true,
            loadMask: true,
            collapsible: false,
            listeners: {
                render: function(grid) {
                    grid.getStore().load();
                }
            }
        });

        this.flowWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.flowWin.setTitle("新增流水");
        this.flowWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.flowWin.setTitle("修改流水");
            this.flowWin.show();
            this.flowWin.load(selected);
        } catch(error) {
            Ext.Msg.alert('系统提示', error.message);
        }
    },

    onDelClick: function() {
        try {
            this.getSelected();
            Ext.Msg.confirm('系统提示', '确定删除该条记录吗?', this.deleteRecord, this);
        }catch(error) {
            Ext.Msg.alert('系统提示', error.message);
        }
    },

    deleteRecord: function(btn) {
        var store = this.getStore();
        var record = this.getSelected();
        var id = record.get('id');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: '/static/data/farespace/fareConfirm/list'+debug,
                params: {idToDel: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.flowWin.hide();
        this.getStore().reload();
    },

    getSelected: function() {
        var sm = this.getSelectionModel();
        if (sm.getCount() == 0) {
            throw new Error('请先选择一条记录');
        }
        return sm.getSelected();
    }
});