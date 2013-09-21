/**
 * 银行凭证tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.bankTicket");

Heat.bankTicket.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.bankTicket.BasicForm.superclass.constructor.call(this, {
            url: '/heatManager/data/fare/bankTicket/update'+debug,
            width: 300,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,

            items: [{
                xtype: 'hidden',
                name: 'ctfid'
            }, new Ext.form.ComboBox({
                hiddenName: 'bnkid',
                mode: 'local',
                width: 160,
                fieldLabel: '银行名称',
                triggerAction: 'query',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/financespace/bankTicket/queryBank"+debug}),
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
            }), {
                xtype: 'textfield',
                fieldLabel: '凭证类型',
                name: 'ctftype',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: '凭证编号',
                name: 'ctfnumber',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: '金额',
                name: 'money',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: '承办人',
                name: 'undertake',
                width: 160
            }, {
                xtype: 'datefield',
                fieldLabel: '凭证时间',
                editable: false,
                format: 'Y-m-d',
                name: 'ctfdate',
                width: 160
            }, {
                xtype: 'datefield',
                fieldLabel: '录入时间',
                editable: false,
                format: 'Y-m-d',
                name: 'importdate',
                width: 160
            }]
        });

        this.addEvents('submitcomplete');
    },

    setValues: function(record) {
        this.getForm().loadRecord(record);
    },

    formSubmit: function() {
        var isInvalid = false,
            form = this.getForm(),
            fields = ["bnkid"];
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

    submitcomplete: function(form, action) {
        this.reset();
        this.fireEvent('submitcomplete');
    }
});


Heat.bankTicket.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.bankTicket.BasicForm();
        Heat.bankTicket.BasicWin.superclass.constructor.call(this, {
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

Heat.bankTicket.RelateGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/fareflow/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'rcdid', type: 'int'},
                    {name: 'ctfnumber', type: 'string'},
                    {name: 'usrid', type: 'int'},
                    {name: 'rcdtime', type: 'string'},
                    {name: 'money', type: 'float'},
                    {name: 'dealname', type: 'string'},
                    {name: 'chgid', type: 'string'}
                ]
            })
        });
        Heat.bankTicket.BasicGrid.superclass.constructor.call(this, {
            store: store,
            columns: [{
                header: "流水号",
                dataIndex: "rcdid",
                width: 2
            }, {
                header: "账号",
                dataIndex: 'ctfnumber',
                width: 2
            }, {
                header: "应缴记录号",
                dataIndex: "chgid",
                width: 4
            }, {
                header: "时间",
                dataIndex: 'rcdtime',
                width: 3
            }, {
                header: "金额",
                dataIndex: "money",
                width: 2
            }, {
                header: "账期",
                dataIndex: "dealname",
                width: 2
            }],

            viewConfig: {
                forceFit: true
            },

            autoScroll: true,
            frame: true,
            height: 200,
            loadMask: true,
            collapsible: false
        });
    },

    refresh: function(ctfid) {
        this.getStore().load({params: {ctfid: ctfid}});
    }
});

Heat.bankTicket.MoneyPane = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.bankTicket.MoneyPane.superclass.constructor.call(this, {
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                layout: 'column',
                items: [{
                    columnWidth: .5,
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        fieldLabel: '凭证金额',
                        name: 'ctfmoney',
                        width: 120,
                        disabled: true
                    }]
                }, {
                    columnWidth: .5,
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        fieldLabel: '流水金额',
                        name: 'money',
                        width: 120,
                        disabled: true
                    }]
                }]
            }]
        });
    },

    setCtfMoney: function(money) {
        this.getForm().findField("ctfmoney").setValue(money);
    },

    update: function(money) {
        this.getForm().findField("money").setValue(money);
    },

    checkMoney: function() {
        var ctfmoney = this.getForm().findField("ctfmoney").getValue(),
            money = this.getForm().findField("money").getValue();
        if (ctfmoney != money) {
            throw Error("金额不一致，请检查后再提交");
        }
    }
});

Heat.bankTicket.ChargeGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var self = this,
            sm = new Ext.grid.CheckboxSelectionModel({
            singleSelect: false,
            listeners: {
                rowselect: function() {
                    var records = this.getSelections(),
                        money = 0;
                    for (var i = 0; i < records.length; i++) {
                        money += records[i].get("money");
                    }
                    self.self.moneyPane.update(money);
                },

                rowdeselect: function() {
                    var records = this.getSelections(),
                        money = 0;
                    for (var i = 0; i < records.length; i++) {
                        money += records[i].get("money");
                    }
                    self.self.moneyPane.update(money);
                }
            }
        });
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/fareflow/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'rcdid', type: 'int'},
                    {name: 'ctfnumber', type: 'string'},
                    {name: 'usrid', type: 'int'},
                    {name: 'rcdtime', type: 'string'},
                    {name: 'money', type: 'float'},
                    {name: 'dealname', type: 'string'},
                    {name: 'chgid', type: 'string'}
                ]
            })
        });
        Heat.bankTicket.ChargeGrid.superclass.constructor.call(this, {
            store: store,
            sm: sm,
            columns: [
                sm,
            {
                header: "流水号",
                dataIndex: "rcdid",
                width: 2
            }, {
                header: "账号",
                dataIndex: 'ctfnumber',
                width: 2
            }, {
                header: "应缴记录号",
                dataIndex: "chgid",
                width: 4
            }, {
                header: "时间",
                dataIndex: 'rcdtime',
                width: 3
            }, {
                header: "金额",
                dataIndex: "money",
                width: 2
            }, {
                header: "账期",
                dataIndex: "dealname",
                width: 2
            }],

            viewConfig: {
                forceFit: true
            },

            autoScroll: true,
            frame: true,
            height: 200,
            loadMask: true,
            collapsible: false
        });
    },

    refresh: function(ctfid) {
        this.clearSelection();
        this.getStore().load({params: {ctfid: ctfid}});
    },

    getSelected: function() {
        var sm = this.getSelectionModel(),
            selected = sm.getSelections();
        if (selected.length == 0) {
            throw Error("请选择一条应缴记录");
        } else {
            return selected;
        }
    },

    clearSelection: function() {
        this.getSelectionModel().selections.items = [];
    }
});

Heat.bankTicket.RelateWin = Ext.extend(Ext.Window, {
    chargeGrid: null,
    moneyPane: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.moneyPane = new Heat.bankTicket.MoneyPane();
        this.chargeGrid = new Heat.bankTicket.ChargeGrid();
        this.chargeGrid.self = this;
        Heat.bankTicket.RelateWin.superclass.constructor.call(this, {
            items: [
                this.moneyPane,
                this.chargeGrid
            ],

            tbar: [{
                text: "关联流水记录",
                iconCls: "add_icon",
                handler: this.relateCharge,
                scope: this
            }],

            listeners: {
                'show': {
                    fn: this.refresh,
                    scope: this
                }
            },

            title: '关联流水记录',
            width: 600,
            buttonAlign: 'center',
            closeAction: 'hide',
            modal: true
        });
    },

    relateCharge: function() {
        try {
            this.moneyPane.checkMoney();
            var self = this,
                records = this.chargeGrid.getSelected(),
                ids = [];
            for (var i = 0, l = records.length; i < l; i++) {
                var record = records[i];
                ids.push(record.get('rcdid'));
            }
            Ext.Ajax.request({
                url: "/heatManager/data/fare/bankTicket/relateCharge"+debug,
                params: {ctfid: self.ctfid, ids: ids},
                success: function(res) {
                    if (!res) {
                        Ext.Msg.alert("系统提示", "服务器未响应");
                    }
                    var r = Ext.decode(res.responseText);
                    if (r.success) {
                        self.refresh();
                    } else {
                        Ext.Msg.alert("系统提示", r.message);
                    }
                },
                failure: function() {
                    Ext.Msg.alert("系统提示", "服务器连接失败");
                }
            });
        } catch(err) {
            Ext.Msg.alert('系统提示', err.message);
        }
    },

    refresh: function() {
        this.moneyPane.setCtfMoney(this.ctfmoney);
        this.chargeGrid.refresh(this.ctfid);
    }
});

Heat.bankTicket.CheckWin = Ext.extend(Ext.Window, {
    relateGrid: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.relateGrid = new Heat.bankTicket.RelateGrid();
        Heat.bankTicket.CheckWin.superclass.constructor.call(this, {
            items: [this.relateGrid],

            listeners: {
                'show': {
                    fn: this.refresh,
                    scope: this
                }
            },

            title: '查看流水记录',
            width: 600,
            buttonAlign: 'center',
            closeAction: 'hide',
            modal: true
        });
    },

    refresh: function() {
        this.relateGrid.refresh(this.ctfid);
    }
});

Heat.bankTicket.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    bankTicketWin: null,
    relateWin: null,
    checkWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.bankTicketWin = new Heat.bankTicket.BasicWin();
        this.relateWin = new Heat.bankTicket.RelateWin();
        this.checkWin = new Heat.bankTicket.CheckWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/bankTicket/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'ctfid', type: 'int'},
                    {name: 'bnkid', type: 'int'},
                    {name: 'bnkname', type: 'string'},
                    {name: 'ctftype', type: 'string'},
                    {name: 'ctfnumber', type: 'string'},
                    {name: 'money', type: 'float'},
                    {name: 'undertake', type: 'string'},
                    {name: 'ctfdate', type: 'string'},
                    {name: 'importdate', type: 'string'},
                    {name: 'importer', type: 'string'},
                    {name: 'relatednum', type: 'int'}
                ]
            })
        });
        Heat.bankTicket.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "记录编号",
                dataIndex: 'ctfid',
                width: 2
            }, {
                header: "银行名称",
                dataIndex: 'bnkname',
                width: 2
            }, {
                header: "凭证类型",
                dataIndex: 'ctftype',
                width: 2
            }, {
                header: "凭证编号",
                dataIndex: 'ctfnumber',
                width: 3
            }, {
                header: "金额",
                dataIndex: 'money',
                width: 2
            }, {
                header: "承办人",
                dataIndex: 'undertake',
                width: 2
            }, {
                header: "凭证时间",
                dataIndex: 'ctfdate',
                width: 2
            }, {
                header: "录入时间",
                dataIndex: "importdate",
                width: 2
            }, {
                header: "录入人",
                dataIndex: "importer",
                width: 2
            }, {
                header: "涉及流水笔数",
                dataIndex: "relatednum",
                width: 5
            }],

            tbar: [{
                text: "添加银行凭证",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改银行凭证",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除银行凭证",
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
                },
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var record = grid.getStore().getAt(rowIndex),
                        ctfid = record.get('ctfid'),
                        num = record.get("relatednum"),
                        money = record.get('money');

                    var menu = new Ext.menu.Menu([{
                        text: "查看流水记录",
                        handler: function() {
                            grid.checkWin.ctfid = ctfid;
                            grid.checkWin.show();
                        }
                    }, {
                        text: "关联流水记录",
                        handler: function() {
                            grid.relateWin.ctfmoney = money;
                            grid.relateWin.ctfid = ctfid;
                            grid.relateWin.show();
                        }
                    }]);
                    if (num == 0 || num == "") {
                        menu.items.items[0].hide();
                    } else {
                        menu.items.items[1].hide();
                    }
                    menu.showAt(e.getPoint());
                }
            }
        });

        this.bankTicketWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.bankTicketWin.setTitle("新增银行凭证");
        this.bankTicketWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.bankTicketWin.setTitle("修改银行凭证");
            this.bankTicketWin.show();
            this.bankTicketWin.load(selected);
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
        var id = record.get('ctfid');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: '/heatManager/data/fare/bankTicket/del'+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.bankTicketWin.hide();
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