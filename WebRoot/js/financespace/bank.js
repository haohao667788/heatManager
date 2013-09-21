/**
 * 科目tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.bank");

Heat.bank.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.bank.BasicForm.superclass.constructor.call(this, {
            url: '/heatManager/data/financespace/bank/update'+debug,
            width: 300,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'bnkid'
            }, {
                xtype: 'textfield',
                fieldLabel: '银行编号',
                name: 'bnknum',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '银行名称',
                name: 'bnkname',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '账号',
                name: 'accountnum',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'crsid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属科目',
                triggerAction: 'query',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: '/heatManager/data/financespace/bank/queryCrs'+debug}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                }),
                listeners: {
                    beforequery: function(con) {
                        if (!con.combo.sel) {
                            con.combo.sel = true;
                            con.combo.getStore().reload();
                        }
                    },
                    blur: function(combo) {
                        combo.sel = false;
                    },
                    change: function(combo, value) {
                        var flag = false;
                        combo.getStore().each(function(record, index, total) {
                            var text = record.get("text"),
                                val = record.get("value");
                            if (val == value || val == text) {
                                flag = true;
                                return false;
                            }
                        });
                        if (!flag) {
                            combo.markInvalid("请选择对应记录");
                        }
                    }
                }
            })]
        });

        this.addEvents('submitcomplete');
    },

    setValues: function(record) {
        this.getForm().loadRecord(record);
    },

    //提交表单数据
    formSubmit: function() {
        var isInvalid = false,
            form = this.getForm(),
            fields = ["crsid"];
        Ext.each(fields, function(field) {
            var $f = $(form.findField(field).el.dom);
            if ($f.hasClass("x-form-invalid")) {
                isInvalid = true;
                return false;
            }
        });
        if (isInvalid) {
            Ext.Msg.alert("系统提示", "请正确填写表单");
        } else {
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
        }
    },

    reset: function() {
        this.getForm().clearInvalid();
        this.getForm().reset();
    },

    //当表单提交成功后，触发complete事件(win由于监听了complete事件能通过得到响应)
    submitcomplete: function(form, action) {
        this.reset();
        this.fireEvent('submitcomplete');
    }
});


Heat.bank.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.bank.BasicForm();
        Heat.bank.BasicWin.superclass.constructor.call(this, {
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
            width: 300,
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


Heat.bank.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    bankWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.bankWin = new Heat.bank.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: '/heatManager/data/financespace/bank/list'+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'bnkid', type: 'int'},
                    {name: 'bnknum', type: 'string'},
                    {name: 'bnkname', type: 'string'},
                    {name: 'accountnum', type: 'string'},
                    {name: 'crsid', type: 'int'},
                    {name: 'crsname', type: 'string'}
                ]
            })
        });
        Heat.bank.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "银行编号",
                dataIndex: 'bnknum',
                width: 1
            }, {
                header: "银行名称",
                dataIndex: 'bnkname',
                width: 2
            }, {
                header: "账号",
                dataIndex: 'accountnum',
                width: 2
            }, {
                header: "科目名称",
                dataIndex: 'crsname',
                width: 2
            }],

            tbar: [{
                text: "添加银行",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改银行",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除银行",
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
            collapsible: false,
            listeners: {
                render: function(grid) {
                    grid.getStore().load();
                }
            }
        });

        this.bankWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.bankWin.setTitle("新增银行");
        this.bankWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.bankWin.setTitle("修改银行");
            this.bankWin.show();
            this.bankWin.load(selected);
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
        var id = record.get('bnkid');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: '/heatManager/data/financespace/bank/del'+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.bankWin.hide();
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