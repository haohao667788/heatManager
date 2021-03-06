/**
 * 用户收费tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.userFare");

/** --收费窗口-- **/
Heat.userFare.FareForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userFare.FareForm.superclass.constructor.call(this, {
            url: '/heatManager/data/farespace/userFare/duefare'+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            columnLines: true,
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'usrid'
            }, {
                xtype: 'hidden',
                name: 'dealname'
            }, {
                xtype: 'hidden',
                name: 'ids'
            }, {
                xtype: 'hidden',
                name: 'dues'
            }, {
                xtype: 'hidden',
                name: 'charges'
            }, {
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
                        name: 'money',
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
                                    value = basicForm.findField("money").getValue(),
                                    v = value == "" ? 0 : value,
                                    accountFare = basicForm.findField("accountPay").checked ? form.ownerCt.accountFare : 0,
                                    left = (+v)+(+accountFare)-(+fare);

                                if (checked) {
                                    Ext.query(".accountFare")[0].innerHTML = accountFare==""?0:accountFare;
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
            }, new Ext.form.ComboBox({
                hiddenName: 'chgtype',
                mode: 'local',
                width: 120,
                fieldLabel: '缴费方式',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                value: 0,
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data: [
                        ['现金', '现金'],
                        ['转账', '转账'],
                        ['刷卡', '刷卡'],
                        ['支票', '支票']
                    ]
                }),
                listeners: {
                    select: function(combo, record) {
                        var value = record.data.value;
                        if (value == '现金') {
                            combo.nextSibling().setValue("");
                            combo.nextSibling().disable();
                        } else {
                            combo.nextSibling().enable();
                        }
                    }
                }
            }), {
                xtype: 'textfield',
                fieldLabel: '对应号码',
                name: 'checknum',
                width: 120,
                disabled: true
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
                xtype: 'fileuploadfield',
                fieldLabel: '原始影像',
                name: 'rcdpic',
                width: 120,
                buttonText: '',
                buttonCfg: {
                    iconCls: 'upload_icon'
                }
            }]
        });

        this.addEvents('submitcomplete');
    },

    //提交表单数据
    formSubmit: function() {
        var money = this.getForm().findField("money").getValue(),
            dues = this.getForm().findField("dues").getValue();
        if (money == "") {
            Ext.Msg.alert("系统提示", "请先填写缴费金额");
            return;
        } else {
            var dueArr = dues.split(","), charges = [];
            for (var i = 0; i < dueArr.length; i++) {
                if (money <= 0) {
                    charges.push(0);
                } else {
                    charges.push(Math.min(dueArr[i], money));
                    money = money-dueArr[i];
                }
            }
            this.getForm().findField("charges").setValue(charges.join(","));
        }
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

    setValues: function(usrid, dealname, ids, fare, dues) {
        this.getForm().findField("usrid").setValue(usrid);
        this.getForm().findField("dealname").setValue(dealname);
        this.getForm().findField("ids").setValue(ids);
        this.getForm().findField("dueFare").setValue(fare);
        this.getForm().findField("dues").setValue(dues);
    },

    submitcomplete: function(form, action) {
        var res = action.response;
        if (!res.responseText) {
            Ext.Msg.alert("系统提示", "服务器未响应");
        } else {
            var r = Ext.decode(res.responseText);
            if (r.success) {
                this.reset();
                this.fireEvent('submitcomplete');
            } else {
                Ext.Msg.alert('系统提示', r.message);
            }
        }
    }
});


Heat.userFare.FareWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.userFare.FareForm();
        Heat.userFare.FareWin.superclass.constructor.call(this, {
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

            title: '收费',
            width: 500,
            buttonAlign: 'center',
            closeAction: 'hide',
            modal: true,
            listeners: {
                show: function(win) {
                    win.form.accountFare = win.accountFare;
                    win.form.setValues(win.usrid, win.dealname, win.ids, win.fare, win.dues);
                },
                hide: {
                    fn: this.onResetClick,
                    scope: this
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

    submitcomplete: function(form, action) {
        var res = action.response;
        if (!res.responseText) {
            Ext.Msg.alert("系统提示", "服务器未响应");
        } else {
            var r = Ext.decode(res.responseText);
            if (r.success) {
                this.reset();
                this.fireEvent('submitcomplete');
            } else {
                Ext.Msg.alert('系统提示', r.message);
            }
        }
    }
});

/** --预收窗口-- **/
Heat.userFare.PreForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userFare.PreForm.superclass.constructor.call(this, {
            url: '/heatManager/data/farespace/userFare/presave'+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            columnLines: true,
            fileUpload: true,
            items: [{
                layout: 'column',
                items: [{
                    xtype: 'hidden',
                    name: 'usrid'
                }, {
                    xtype: 'hidden',
                    name: 'dealname'
                }, {
                    xtype: 'hidden',
                    name: 'curCharge'
                }, {
                    columnWidth: .5,
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        fieldLabel: '账期数目',
                        name: 'dealnum',
                        width: 120,
                        allowBlank: false,
                        regex:/^\d$/,
                        regexText: "请输入数字",
                        listeners: {
                            change: function(field, n) {
                                var form = field.ownerCt.ownerCt.ownerCt,
                                    basicForm = form.getForm(),
                                    fare = basicForm.findField("curCharge").getValue(),
                                    af = basicForm.findField("actualFare").getValue(),
                                    v = n == "" ? 0 : n,
                                    af = af == "" ? 0 : af;

                                basicForm.findField("dueFare").setValue(fare*n);
                                //basicForm.findField("leftFare").setValue((+af)-(fare*n));
                            }
                        }
                    }, {
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
                        allowBlank: false
                    }]
                }]
            }, {
                width: 470,
                bodyStyle: {
                    borderTop: '1px solid #ccc',
                    margin: '10px 0'
                }
            }, new Ext.form.ComboBox({
                hiddenName: 'chgtype',
                mode: 'local',
                width: 120,
                fieldLabel: '缴费方式',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                value: 0,
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data: [
                        ['现金', '现金'],
                        ['转账', '转账'],
                        ['刷卡', '刷卡'],
                        ['支票', '支票']
                    ]
                }),
                listeners: {
                    select: function(combo, record) {
                        var value = record.data.value;
                        if (value == 0) {
                            combo.nextSibling().setValue("");
                            combo.nextSibling().disable();
                        } else {
                            combo.nextSibling().enable();
                        }
                    }
                }
            }), {
                xtype: 'textfield',
                fieldLabel: '对应号码',
                name: 'checknum',
                width: 120,
                disabled: true
            }, {
                xtype: 'fileuploadfield',
                fieldLabel: '原始影像',
                name: 'rcdpic',
                width: 120,
                buttonText: '',
                buttonCfg: {
                    iconCls: 'upload_icon'
                }
            }]
        });

        this.addEvents('submitcomplete');
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

    setValues: function(usrid, dealname, fare) {
        this.getForm().findField("usrid").setValue(usrid);
        this.getForm().findField("dealname").setValue(dealname);
        this.getForm().findField("curCharge").setValue(fare);
    },

    submitcomplete: function(form, action) {
        this.reset();
        this.fireEvent('submitcomplete');
    }
});


Heat.userFare.PreWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.userFare.PreForm();
        Heat.userFare.PreWin.superclass.constructor.call(this, {
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
                hide: {
                    fn: this.onResetClick,
                    scope: this
                },
                show: function(win) {
                    win.form.setValues(win.usrid, win.dealname, win.curCharge);
                }
            },

            title: '预存',
            width: 500,
            buttonAlign: 'center',
            closeAction: 'hide',
            modal: true
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

Heat.userFare.AccountGrid = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        var doc = document.documentElement || document.body;
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userFare.FareForm.superclass.constructor.call(this, {
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            columnLines: true,
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
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '户主姓名',
                            name: 'usrname',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'hidden',
                            name: 'bldid'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '所在楼栋',
                            name: 'bldname',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'hidden',
                            name: "mchid"
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '所属机组',
                            name: 'mchname',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '合同版本',
                            name: 'contractver',
                            width: 120,
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '用户类型',
                            name: 'usrtype',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'hidden',
                            name: 'untid'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '所属单元',
                            name: 'untname',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '开户时间',
                            name: 'startdate',
                            width: 120,
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'hidden',
                            name: 'pjtid'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '所属项目',
                            name: 'pjtname',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '用户地址',
                            name: 'address',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '合同签订时间',
                            name: 'contractdate',
                            width: 120,
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'hidden',
                            name: 'cmtid'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '所属社区',
                            name: 'cmtname',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '联系方式',
                            name: 'phone',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '合同类型',
                            name: 'contracttype',
                            width: 120,
                            disabled: true
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
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '建筑面积',
                            name: 'area',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '费率',
                            name: 'feerate',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '热计量基数',
                            name: 'heatbase',
                            width: 120,
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '套内面积',
                            name: 'realarea',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '折扣',
                            name: 'discount',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '热计量费率',
                            name: 'heatrate',
                            width: 120,
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '计费面积',
                            name: 'feearea',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '减免额',
                            name: 'reducefee',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '户型',
                            name: 'housetype',
                            width: 120,
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            fieldLabel: '计费方式',
                            name: 'feetype',
                            width: 120,
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '供热状态',
                            name: 'heatstate',
                            width: 120,
                            disabled: true
                        }]
                    }]
                }, {
                    xtype: 'textarea',
                    fieldLabel: '备注',
                    name: 'desp',
                    width: 438,
                    height: 50,
                    disabled: true
                }]
            }, {
                xtype: 'fieldset',
                title: '账户信息',
                collapsible: false,
                items: [{
                    layout: 'column',
                    items: [{
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            name: 'dealname',
                            fieldLabel: '所属收费年度',
                            disabled: true
                        }, {
                            xtype: 'textfield',
                            name: 'lastdate',
                            fieldLabel: '最后缴费时间',
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            name: 'curbalance',
                            fieldLabel: '本期余额',
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            name: 'curcharge',
                            fieldLabel: '本期应缴',
                            disabled: true
                        }]
                    }, {
                        columnWidth:.25,
                        layout: 'form',
                        items: [{
                            xtype: 'textfield',
                            name: 'curmoney',
                            fieldLabel: '本期实缴',
                            disabled: true
                        }]
                    }]
                }]
            }]
        });
    },

    getUserId: function() {
        return this.getForm().findField("usrid").getValue();
    },

    getAccountFare: function() {
        return this.getForm().findField("curbalance").getValue();
    },

    getDealname: function() {
        return this.getForm().findField("dealname").getValue();
    },

    getCurcharge: function() {
        return this.getForm().findField("curcharge").getValue();
    },

    getSelected: function() {
        var sm = this.getSelectionModel();
        if (sm.getCount() == 0) {
            throw new Error('请先选择一条账户信息');
        }
        return sm.getSelected();
    },

    refresh: function() {
        var self = this;
        Ext.Ajax.request({
            url: '/heatManager/data/farespace/userFare/queryDue'+debug,
            params: {usrid: self.usrid},
            success: function(res) {
                var response = res.responseText,
                    data = Ext.decode(response);
                if (data.success) {
                    self.getForm().setValues(data.data);
                } else {
                    Ext.Msg.alert('系统提示', data.message);
                }
            },
            failure: function() {
                Ext.Msg.alert('系统提示', '服务器通信失败');
            }
        });
    },

    setValues: function(record) {
        this.getForm().loadRecord(record);
    }
});

Heat.userFare.FareFlowGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        var doc = document.documentElement || document.body;
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/fareconfirm/getchargerecord"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'rcdid', type: 'int'},
                    {name: 'ctfid', type: 'int'},
                    {name: 'chgid', type: 'string'},
                    {name: 'ctfnumber', type: 'string'},
                    {name: 'rcdtime', type: 'string'},
                    {name: 'money', type: 'float'},
                    {name: 'rate', type: 'float'},
                    {name: 'chgtype', type: 'string'},
                    {name: 'checknum', type: 'string'},
                    {name: 'rcdpic', type: 'string'},
                    {name: 'dealname', type: 'string'},
                    {name: 'stfid', type: 'int'},
                    {name: 'stfname', type: 'string'},
                    {name: 'financechecker', type: 'string'},
                    {name: 'chargeverifytime', type: 'string'}
                ]
            })
        });
        Heat.userFare.FareFlowGrid.superclass.constructor.call(this, {
            store: store,

            title: '流水记录',
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
                header: "缴费方式",
                dataIndex: "chgtype",
                width: 2
            }, {
                header: "对应号码",
                dataIndex: "checknum",
                width: 3
            }, {
                header: "账期",
                dataIndex: "dealname",
                width: 2
            }, {
                header: "收费员",
                dataIndex: "stfname",
                width: 3
            }, {
                header: "核对员",
                dataIndex: "financechecker",
                width: 3
            }, {
                header: "核对时间",
                dataIndex: "chargeverifytime",
                width: 4
            }],

            viewConfig: {
                forceFit: true
            },

            frame: true,
            loadMask: true,
            collapsible: false,
            height: 200,
            listeners: {
                render: this.refresh
            }
        });
    },

    refresh: function() {
        this.getStore().load({params: {"usrid": this.self.usrid}});
    }
});

Heat.userFare.RecordGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        var doc = document.documentElement || document.body,
            sm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/fareconfirm/getduecharge"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'chgid', type: 'int'},
                    {name: 'usrid', type: 'string'},
                    {name: 'dealname', type: 'string'},
                    {name: 'chgtype', type: 'string'},
                    {name: 'area', type: 'float'},
                    {name: 'rate', type: 'float'},
                    {name: 'charge', type: 'float'},
                    {name: 'money', type: 'float'},
                    {name: 'lastchgtime', type: 'string'}
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
                dataIndex: "chgid",
                width: 2
            }, {
                header: "用户账号",
                dataIndex: 'usrid',
                width: 2
            }, {
                header: "所属收费年度",
                dataIndex: 'dealname',
                width: 2
            }, {
                header: "缴费类型",
                dataIndex: "chgtype",
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
                dataIndex: "charge",
                width: 2
            }, {
                header: "实缴金额",
                dataIndex: "money",
                width: 2
            }, {
                header: "最后一次缴费时间",
                dataIndex: "lastchgtime",
                width: 4
            }],

            viewConfig: {
                forceFit: true
            },

            frame: true,
            loadMask: true,
            collapsible: false,
            height: doc.clientHeight-520,
            listeners: {
                render: this.refresh
            }
        });
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
    },

    refresh: function(grid) {
        this.clearSelection();
        this.getStore().load({params: {"usrid": this.self.usrid}});
    }
});

Heat.userFare.BasicGrid = Ext.extend(Ext.Panel, {
    accountGrid: null,
    fareflowGrid: null,
    recordGrid: null,
    fareWin: null,
    preWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.accountGrid = new Heat.userFare.AccountGrid();
        this.fareflowGrid = new Heat.userFare.FareFlowGrid();
        this.recordGrid = new Heat.userFare.RecordGrid();
        this.accountGrid.self = this;
        this.fareflowGrid.self = this;
        this.recordGrid.self = this;
        this.fareWin = new Heat.userFare.FareWin();
        this.preWin = new Heat.userFare.PreWin();

        Heat.userFare.BasicGrid.superclass.constructor.call(this, {
            frame: true,
            loadMask: true,
            collapsible: false,
            autoScroll: true,

            tbar: [{
                xtype: 'button',
                iconCls: "fare_icon",
                text: '收费',
                handler: this.onFareClick,
                scope: this
            }, '-', {
                xtype: 'button',
                iconCls: "account_icon",
                text: '预存',
                handler: this.onSaveClick,
                scope: this
            }],

            items: [
                this.accountGrid,
                this.fareflowGrid,
                this.recordGrid
            ],

            listeners: {
                render: function() {
                    this.accountGrid.setValues(this.record);
                }
            }
        });

        this.fareWin.on('submitcomplete', this.refresh, this);
        this.preWin.on('submitcomplete', this.refresh, this);
    },

    onFareClick: function() {
        try {
            var records = this.recordGrid.getSelected(),
                sum = 0,
                ids = [],
                dues = [];
            for (var i = 0, l = records.length; i < l; i++) {
                var record = records[i],
                    due = record.get('charge')-record.get('money');
                sum += due;
                ids.push(record.get('chgid'));
                dues.push(due);
            }
            this.fareWin.fare = sum;
            this.fareWin.ids = ids.join(',');
            this.fareWin.dues = dues.join(',');
            this.fareWin.usrid = this.accountGrid.getUserId();
            this.fareWin.dealname = this.accountGrid.getDealname();
            this.fareWin.accountFare = this.accountGrid.getAccountFare();
            this.fareWin.show();
        } catch(err) {
            Ext.Msg.alert('系统提示', err.message);
        }
    },

    onSaveClick: function() {
        this.preWin.usrid = this.accountGrid.getUserId();
        this.preWin.dealname = this.accountGrid.getDealname();
        this.preWin.curCharge = this.accountGrid.getCurcharge();
        this.preWin.show();
    },

    refresh: function() {
        this.fareWin.hide();
        this.preWin.hide();
        this.accountGrid.refresh();
        this.fareflowGrid.refresh();
        this.recordGrid.refresh();
    }
});