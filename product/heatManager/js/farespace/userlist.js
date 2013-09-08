/**
 * 用户tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.userlist");

Heat.userlist.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userlist.BasicForm.superclass.constructor.call(this, {
            url: '/heatManager/data/farespace/userlist/update'+debug,
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
                            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/userlist/queryShequ"+debug}),
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
                            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/userlist/queryLoudong"+debug}),
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
                            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/userlist/queryDanyuan"+debug}),
                            reader: new Ext.data.ArrayReader({}, [
                                {name: 'value'},
                                {name: 'text'}
                            ])
                        })
                    }), {
                        xtype: 'textfield',
                        fieldLabel: '用户地址',
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
                            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/userlist/queryUnit"+debug}),
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


Heat.userlist.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.userlist.BasicForm();
        Heat.userlist.BasicWin.superclass.constructor.call(this, {
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


Heat.userlist.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    userlistWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.userlistWin = new Heat.userlist.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/userlist/list"+debug}),
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
                    {name: 'usraddress', type: 'string'},
                    {name: 'usrname', type: 'string'},
                    {name: 'communicate', type: 'string'},
                    {name: 'kaihuDate', type: 'string'},
                    {name: 'contractDate', type: 'string'},
                    {name: 'contracttype', type: 'string'},
                    {name: 'contractversion', type: 'string'},
                    {name: 'contractpic', type: 'string'},
                    {name: 'idpic', type: 'string'},
                    {name: 'fangchanpic', type: 'string'},
                    {name: 'huxingpic', type: 'string'}
                ]
            })
        });
        Heat.userlist.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
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
                dataIndex: 'usraddress',
                width: 160
            }, {
                header: "联系方式",
                dataIndex: 'communicate',
                width: 120
            }, {
                header: "所属机组",
                dataIndex: 'mchname',
                width: 120
            }, {
                header: "开户时间",
                dataIndex: 'kaihuDate',
                width: 100
            }, {
                header: "合同签订时间",
                dataIndex: "contractDate",
                width: 100
            }, {
                header: "合同类型",
                dataIndex: "contracttype",
                width: 100
            }, {
                header: "合同版本",
                dataIndex: "contractversion",
                width: 80
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
                forceFit: false
            },

            frame: true,
            loadMask: true,
            collapsible: false,
            listeners: {
                render: function(grid) {
                    grid.getStore().load();
                },
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var menu = new Ext.menu.Menu([{
                        text: "用户收费",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                usrid = record.get('usrid'),
                                newGrid = new Heat.userFare.BasicGrid();

                            newGrid.usrid = usrid;
                            var tab = Heat.tabs.add({
                                title: "用户收费",
                                //iconCls: 'fwxtabpanelicon',
                                border: 0,
                                autoWidth: true,
                                closable: true,
                                layout: 'fit',
                                items: [newGrid]
                            });
                            Heat.tabs.setActiveTab(tab);

                        }
                    }, {
                        text: "显示楼栋平面图",
                        handler: function() {
                            console.log(rowIndex);
                        }
                    }]);
                    menu.showAt(e.getPoint());
                }
            }
        });

        this.userlistWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.userlistWin.setTitle("新增用户");
        this.userlistWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.userlistWin.setTitle("修改用户");
            this.userlistWin.show();
            this.userlistWin.load(selected);
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
                url: '/heatManager/data/farespace/userlist/list'+debug,
                params: {idToDel: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.userlistWin.hide();
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