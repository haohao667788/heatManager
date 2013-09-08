/**
 * 用户收费tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.userFare");

Heat.userFare.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userFare.BasicForm.superclass.constructor.call(this, {
            url: '/heatManager/data/farespace/userFare/update'+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            columnLines: true,
            items: [{
                layout: 'column',
                items: [{
                    columnWidth: .5,
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        fieldLabel: '应缴费用',
                        name: 'dueFare',
                        width: 120,
                        allowBlank: false,
                        disabled: true
                    }]
                }, {
                    columnWidth: .5,
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        fieldLabel: '收取费用',
                        name: 'actualFare',
                        width: 120,
                        allowBlank: false,
                        listeners: {
                            change: function(input, value) {
                                var form = input.ownerCt.ownerCt.ownerCt,
                                    basicForm = form.getForm(),
                                    fare = basicForm.findField("dueFare").getValue(),
                                    v = value == "" ? 0 : value,
                                    accountFare = basicForm.findField("accountPay").checked ? form.ownerCt.accountFare : 0,
                                    left = (+v)+(+accountFare)-(+fare);

                                basicForm.findField("leftFare").setValue(left);
                            }
                        }
                    }, {
                        xtype: 'checkbox',
                        boxLabel: '使用存款支付',
                        name: 'accountPay',
                        width: 120,
                        listeners: {
                            check: function(checkbox, checked) {
                                var form = checkbox.ownerCt.ownerCt.ownerCt,
                                    basicForm = form.getForm(),
                                    fare = basicForm.findField("dueFare").getValue(),
                                    value = basicForm.findField("actualFare").getValue(),
                                    v = value == "" ? 0 : value,
                                    accountFare = basicForm.findField("accountPay").checked ? form.ownerCt.accountFare : 0,
                                    left = (+v)+(+accountFare)-(+fare);

                                if (checked) {
                                    Ext.query(".accountFare")[0].innerHTML = accountFare;
                                } else {
                                    Ext.query(".accountFare")[0].innerHTML = 0;
                                }
                                basicForm.findField("leftFare").setValue(left);
                            }
                        }
                    }]
                }]
            }, {
                width: 470,
                bodyStyle: {
                    borderTop: '1px solid #ccc',
                    margin: '10px 0'
                }
            }, {
                xtype: 'textfield',
                fieldLabel: '剩余费用',
                name: 'leftFare',
                width: 120,
                allowBlank: false,
                disabled: true
            }, {
                html: '使用存款<span class="accountFare" style="color:red;">0</span>元',
                bodyStyle: {
                    margin: '0 0 10px 85px'
                }
            }, {
                xtype: 'radiogroup',
                fieldLabel: '余额处理方式',
                columns: 1,
                bodyStyle: {
                    "marginLeft": "2px"
                },
                items: [
                    {boxLabel: '将余额存入账户', name: 'method', value: 'save', checked: true},
                    {boxLabel: '将余额返予顾客', name: 'method', value: 'return'}
                ]
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

    setDueFare: function(fare) {
        this.getForm().findField("dueFare").setValue(fare);
    },

    submitcomplete: function(form, action) {
        this.fireEvent('submitcomplete');
    }
});


Heat.userFare.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.userFare.BasicForm();
        Heat.userFare.BasicWin.superclass.constructor.call(this, {
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

            title: '收费',
            width: 500,
            buttonAlign: 'center',
            closeAction: 'hide',
            modal: true,
            listeners: {
                show: function(win) {
                    win.form.setDueFare(win.fare);
                }
            }
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
        this.form.getForm().findField("dueFare").setValue(this.fare);
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

Heat.userFare.AccountGrid = Ext.extend(Ext.grid.GridPanel, {
    userFareWin: null,
    constructor: function(cfg) {
        var doc = document.documentElement || document.body;
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.userFareWin = new Heat.userFare.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/account/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'id', type: 'int'},
                    {name: 'year', type: 'string'},
                    {name: 'lastYue', type: 'float'},
                    {name: 'thisYue', type: 'float'},
                    {name: 'dueFare', type: 'float'},
                    {name: 'actualFare', type: 'float'},
                    {name: 'lastFareTime', type: 'string'},
                    {name: 'accountFare', type: 'float'}
                ]
            })
        });
        Heat.userFare.AccountGrid.superclass.constructor.call(this, {
            title: '账户信息',
            store: store,
            columns: [
                new Ext.grid.RowNumberer(),
            {
                header: "所属收费年度",
                dataIndex: "year",
                width: 1
            }, {
                header: "上期余额",
                dataIndex: 'lastYue',
                width: 2
            }, {
                header: "本期余额",
                dataIndex: 'thisYue',
                width: 2
            }, {
                header: "本期应缴",
                dataIndex: 'dueFare',
                width: 2
            }, {
                header: "本期实缴",
                dataIndex: 'actualFare',
                width: 2
            }, {
                header: "最后一次缴费时间",
                dataIndex: 'lastFareTime',
                width: 2
            }],

            viewConfig: {
                forceFit: true
            },

            frame: true,
            loadMask: true,
            collapsible: false,
            height: (doc.clientHeight-100)/2,
            listeners: {
                render: function(grid) {
                    grid.getStore().load();
                },
                rowclick: function(grid, rowIndex, e) {
                    var id = grid.getStore().getAt(rowIndex).get("id");
                    grid.self.loadDueFare(id);
                }
            }
        });

        this.userFareWin.on("submitcomplete", this.refresh, this);
    },

    getSelected: function() {
        var sm = this.getSelectionModel();
        if (sm.getCount() == 0) {
            throw new Error('请先选择一条账户信息');
        }
        return sm.getSelected();
    }
});

Heat.userFare.RecordGrid = Ext.extend(Ext.grid.GridPanel, {
    userFareWin: null,
    constructor: function(cfg) {
        var doc = document.documentElement || document.body,
            sm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.userFareWin = new Heat.userFare.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/record/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'id', type: 'int'},
                    {name: 'usrid', type: 'string'},
                    {name: 'year', type: 'string'},
                    {name: 'type', type: 'string'},
                    {name: 'area', type: 'float'},
                    {name: 'rate', type: 'float'},
                    {name: 'dueFare', type: 'float'},
                    {name: 'actualFare', type: 'float'},
                    {name: 'lastFareTime', type: 'string'}
                ]
            })
        });
        Heat.userFare.RecordGrid.superclass.constructor.call(this, {
            store: store,

            title: '应缴记录',
            sm: sm,
            columns: [
                sm,
            {
                header: "记录编号",
                dataIndex: "id",
                width: 2
            }, {
                header: "用户账号",
                dataIndex: 'usrid',
                width: 2
            }, {
                header: "所属收费年度",
                dataIndex: 'year',
                width: 2
            }, {
                header: "缴费类型",
                dataIndex: "type",
                width: 2
            }, {
                header: "应缴面积",
                dataIndex: "area",
                width: 2
            }, {
                header: "费率",
                dataIndex: "rate",
                width: 2
            }, {
                header: "应缴金额",
                dataIndex: "dueFare",
                width: 2
            }, {
                header: "实缴金额",
                dataIndex: "actualFare",
                width: 2
            }, {
                header: "最后一次缴费时间",
                dataIndex: "lastFareTime",
                width: 4
            }],

            viewConfig: {
                forceFit: true
            },

            frame: true,
            loadMask: true,
            collapsible: false,
            height: (doc.clientHeight-190)/2
        });

        this.userFareWin.on("submitcomplete", this.refresh, this);
    },

    getSelected: function() {
        var sm = this.getSelectionModel();
        return sm.getSelections();
    }
});

Heat.userFare.BasicGrid = Ext.extend(Ext.Panel, {
    accountGrid: null,
    recordGrid: null,
    fareWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.accountGrid = new Heat.userFare.AccountGrid();
        this.recordGrid = new Heat.userFare.RecordGrid();
        this.accountGrid.self = this;
        this.recordGrid.self = this;
        this.fareWin = new Heat.userFare.BasicWin();

        Heat.userFare.BasicGrid.superclass.constructor.call(this, {
            frame: true,
            loadMask: true,
            collapsible: false,

            tbar: [{
                xtype: 'button',
                iconCls: "fare_icon",
                text: '收费',
                handler: this.onFareClick,
                scope: this
            }],

            items: [
                this.accountGrid,
                this.recordGrid
            ],
            listeners: {
                render: function(grid) {
                    //grid.getStore().load();
                }
            }
        });
    },

    onFareClick: function() {
        try {
            var user = this.accountGrid.getSelected(),
                records = this.recordGrid.getSelected(),
                sum = 0,
                ids = [];
            for (var i = 0, l = records.length; i < l; i++) {
                var record = records[i];
                sum += record.get('dueFare');
                ids.push(record.get('id'));
            }
            this.fareWin.fare = sum;
            this.fareWin.ids = ids.join(',');
            this.fareWin.accountFare = user.get("accountFare");
            this.fareWin.show();
        } catch(err) {
            Ext.Msg.alert('系统提示', err.message);
        }
    },

    loadDueFare: function(id) {
        this.recordGrid.getStore().load({params: {id: id}});
    },

    refresh: function() {
        this.accountGrid.getStore().reload();
        this.recordGrid.getStore().reload();
    }
});