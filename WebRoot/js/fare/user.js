/**
 * 用户管理tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.user");

Heat.user.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.user.BasicForm.superclass.constructor.call(this, {
            url: '/heatManager/data/fare/user/update'+debug,
            width: 588,
            height: 520,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'usrid'
            }, {
                xtype: 'fieldset',
                title: '基础信息',
                collapsible: false,
                items: [{
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
                            fieldLabel: '用户类型',
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
                                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/user/queryShequ"+debug}),
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
                                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/user/queryLoudong"+debug}),
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
                                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/user/queryDanyuan"+debug}),
                                reader: new Ext.data.ArrayReader({}, [
                                    {name: 'value'},
                                    {name: 'text'}
                                ])
                            })
                        }), {
                            xtype: 'textfield',
                            fieldLabel: '用户地址',
                            name: 'address',
                            width: 160,
                            allowBlank: false
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '联系方式',
                            name: 'phone',
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
                                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/user/queryUnit"+debug}),
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
                            name: 'startdate',
                            width: 160
                        }, {
                            xtype: 'datefield',
                            fieldLabel: '合同签订时间',
                            editable: false,
                            format: 'Y-m-d',
                            name: 'contractdate',
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
                            name: 'contractver',
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
                            name: 'houseidpic',
                            width: 160,
                            buttonText: '',
                            buttonCfg: {
                                iconCls: 'upload_icon'
                            }
                        }, {
                            xtype: 'fileuploadfield',
                            fieldLabel: '户型图纸影像',
                            name: 'housepic',
                            width: 160,
                            buttonText: '',
                            buttonCfg: {
                                iconCls: 'upload_icon'
                            }
                        }]
                    }]
                }]
            }, {
                xtype: 'fieldset',
                title: '计费信息',
                collapsible: false,
                items: [{
                    layout: 'column',
                    items: [{
                        columnWidth:.5,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '建筑面积',
                            name: 'area',
                            width: 160,
                            allowBlank: false
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '套内面积',
                            name: 'realarea',
                            width: 160,
                            allowBlank: false
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '计费面积',
                            name: 'feearea',
                            width: 160,
                            allowBlank: false
                        }, new Ext.form.ComboBox({
                            hiddenName: 'feetype',
                            mode: 'local',
                            width: 160,
                            fieldLabel: '计费方式',
                            triggerAction: 'all',
                            valueField: 'value',
                            displayField: 'text',
                            allowBlank: false,
                            editable: false,
                            store: new Ext.data.SimpleStore({
                                fields: ['value', 'text'],
                                data: [
                                    [0, '按建筑面积'],
                                    [1, '按套内面积'],
                                    [2, '热计量费率']
                                ]
                            }),
                            listeners: {
                                select: function(combo) {
                                    var form = combo.ownerCt.ownerCt.ownerCt.ownerCt,
                                        basicForm = form.getForm(),
                                        heatbase = basicForm.findField("heatbase"),
                                        heatrate = basicForm.findField("heatrate"),
                                        type = combo.getValue();
                                    if (type == 2 || type == "热计量费率") {
                                        heatbase.enable();
                                        heatrate.enable();
                                    } else {
                                        heatbase.disable();
                                        heatrate.disable();
                                    }

                                }
                            }
                        }), {
                            xtype: 'textfield',
                            fieldLabel: '费率',
                            name: 'feerate',
                            width: 160,
                            allowBlank: false
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '折扣',
                            name: 'discount',
                            width: 160,
                            allowBlank: false
                        }]
                    }, {
                        columnWidth:.5,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '减免额',
                            name: 'reducefee',
                            width: 160,
                            allowBlank: false
                        }, new Ext.form.ComboBox({
                            hiddenName: 'heatstate',
                            mode: 'local',
                            width: 160,
                            fieldLabel: '供热状态',
                            triggerAction: 'all',
                            valueField: 'value',
                            displayField: 'text',
                            allowBlank: false,
                            editable: false,
                            store: new Ext.data.SimpleStore({
                                fields: ['value', 'text'],
                                data: [
                                    [0, '开通'],
                                    [1, '关停'],
                                    [2, '限制温度']
                                ]
                            })
                        }), {
                            xtype: 'textfield',
                            fieldLabel: '热计量基数',
                            name: 'heatbase',
                            width: 160,
                            allowBlank: false,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '热计量费率',
                            name: 'heatrate',
                            width: 160,
                            allowBlank: false,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '户型',
                            name: 'housetype',
                            width: 160,
                            allowBlank: false
                        }]
                    }]
                }, {
                    xtype: 'textarea',
                    fieldLabel: '备注',
                    name: 'desp',
                    width: 438,
                    height: 50
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


Heat.user.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.user.BasicForm();
        Heat.user.BasicWin.superclass.constructor.call(this, {
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

Heat.user.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    userWin: null,
    expander: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.userWin = new Heat.user.BasicWin();
        this.expander = new Ext.grid.RowExpander({
            tpl: new Ext.Template(
                '<table style="margin-top:10px;padding-top:10px;width:100%;border-top:2px solid #999;" border=0 cellpadding=0 cellspacing=0>',
                '<tr><td width="25%">所属机组：{mchname}</td>',
                '<td width="25%">开户时间：{startdate}</td>',
                '<td width="25%">合同签订时间：{contractdate}</td>',
                '<td width="25%">合同类型：{contracttype}</td></tr>',
                '<tr><td width="25%">合同版本：{contractver}</td>',
                '<td width="25%">建筑面积：{area}</td>',
                '<td width="25%">套内面积：{realarea}</td>',
                '<td width="25%">计费面积：{feearea}</td></tr>',
                '<tr><td width="25%">计费方式：{feetype}</td>',
                '<td width="25%">费率（元/平米）：{feerate}</td>',
                '<td width="25%">折扣：{discount}</td>',
                '<td width="25%">减免额：{reducefee}</td></tr>',
                '<tr><td width="25%">供热状态：{heatstate}</td>',
                '<td width="25%">热计量基数：{heatbase}</td>',
                '<td width="25%">热计量费率：{heatrate}</td>',
                '<td width="25%">户型：{housetype}</td></tr>',
                '<tr><td width="100%">备注：{desp}</td></tr>',
                '</table>'
            )
        });
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/user/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'usrid', type: 'int'},
                    {name: 'usrtype', type: 'string'},
                    {name: 'cmtid', type: 'int'},
                    {name: 'cmtname', type: 'string'},
                    {name: 'bldid', type: 'int'},
                    {name: 'bldname', type: 'string'},
                    {name: 'untid', type: 'int'},
                    {name: 'untname', type: 'string'},
                    {name: 'mchid', type: 'int'},
                    {name: 'mchname', type: 'string'},
                    {name: 'address', type: 'string'},
                    {name: 'usrname', type: 'string'},
                    {name: 'phone', type: 'string'},
                    {name: 'startdate', type: 'string'},
                    {name: 'contractdate', type: 'string'},
                    {name: 'contracttype', type: 'string'},
                    {name: 'contractver', type: 'string'},
                    {name: 'contractpic', type: 'string'},
                    {name: 'idpic', type: 'string'},
                    {name: 'houseidpic', type: 'string'},
                    {name: 'housepic', type: 'string'},
                    {name: 'feeid', type: 'int'},
                    {name: 'usrid', type: 'int'},
                    {name: 'area', type: 'float'},
                    {name: 'realarea', type: 'float'},
                    {name: 'feearea', type: 'float'},
                    {name: 'feetype', type: 'string'},
                    {name: 'feerate', type: 'float'},
                    {name: 'discount', type: 'float'},
                    {name: 'reducefee', type: 'float'},
                    {name: 'heatstate', type: 'string'},
                    {name: 'heatbase', type: 'float'},
                    {name: 'heatrate', type: 'float'},
                    {name: 'housetype', type: 'string'},
                    {name: 'desp', type: 'string'}
                ]
            })
        });
        Heat.user.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [
                this.expander,
            {
                header: "用户编号",
                dataIndex: 'usrid',
                width: 80
            }, {
                header: "户主姓名",
                dataIndex: 'usrname',
                width: 100
            }, {
                header: "用户类型",
                dataIndex: 'usrtype',
                width: 80
            }, {
                header: "所在社区",
                dataIndex: 'cmtname',
                width: 160
            }, {
                header: "所在楼栋",
                dataIndex: 'bldname',
                width: 160
            }, {
                header: "所在单元",
                dataIndex: 'untname',
                width: 160
            }, {
                header: "用户地址",
                dataIndex: 'address',
                width: 160
            }, {
                header: "联系方式",
                dataIndex: 'phone',
                width: 120
            }],

            tbar: [{
                text: "添加用户",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改用户",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除用户",
                iconCls: "del_icon",
                handler: this.onDelClick,
                scope: this
            }],

            bbar: new Ext.PagingToolbar({
                pageSize: 20,
                store: store,
                displayInfo: true,
                displayMsg: '第 {0} - {1} 条，共 {2} 条',
                emptyMsg: '没有记录'
            }),

            viewConfig: {
                forceFit: true
            },

            frame: true,
            loadMask: true,
            plugins: this.expander,
            listeners: {
                render: function(grid) {
                    grid.getStore().load();
                }
            }
        });

        this.userWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.userWin.setTitle("新增用户");
        this.userWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.userWin.setTitle("修改用户");
            this.userWin.show();
            this.userWin.load(selected);
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
                url: '/heatManager/data/fare/user/del'+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.userWin.hide();
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