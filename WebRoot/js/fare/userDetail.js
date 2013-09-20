/**
 * 用户详情tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.userDetail");

Heat.userDetail.AccountGrid = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        var doc = document.documentElement || document.body;
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userDetail.AccountGrid.superclass.constructor.call(this, {
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            columnLines: true,
            autoScroll: true,
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
                            xtype: "hidden",
                            name: 'accrangeid'
                        }, {
                            xtype: 'textfield',
                            name: 'finacerange',
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

    refresh: function() {
        var self = this;
        Ext.Ajax.request({
            url: '/heatManager/data/farespace/fareConfirm/queryDue'+debug,
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

Heat.userDetail.FareFlowGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        var doc = document.documentElement || document.body;
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/fareConfirm/listFlow"+debug}),
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
        Heat.userDetail.FareFlowGrid.superclass.constructor.call(this, {
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

Heat.userDetail.RecordGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        var doc = document.documentElement || document.body;
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/farespace/fareConfirm/list"+debug}),
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
        Heat.userDetail.RecordGrid.superclass.constructor.call(this, {
            store: store,

            title: '应缴记录',
            columns: [
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

    refresh: function() {
        this.getStore().load({params: {"usrid": this.self.usrid}});
    }
});

Heat.userDetail.BasicGrid = Ext.extend(Ext.Panel, {
    accountGrid: null,
    fareflowGrid: null,
    recordGrid: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.accountGrid = new Heat.userDetail.AccountGrid();
        this.fareflowGrid = new Heat.userDetail.FareFlowGrid();
        this.recordGrid = new Heat.userDetail.RecordGrid();
        this.accountGrid.self = this;
        this.fareflowGrid.self = this;
        this.recordGrid.self = this;

        Heat.userDetail.BasicGrid.superclass.constructor.call(this, {
            frame: true,
            loadMask: true,
            collapsible: false,
            autoScroll: true,

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
    }
});