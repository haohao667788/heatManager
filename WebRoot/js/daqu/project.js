/**
 * 项目tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.project");

Heat.project.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.project.BasicForm.superclass.constructor.call(this, {
            url: "/heatManager/data/daqu/project/update"+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                xtype: 'hidden',
                name: 'pjtid'
            }, {
                xtype: 'textfield',
                fieldLabel: '项目编号',
                name: 'pjtnum',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '项目名称',
                name: 'pjtname',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'ctyid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属行政区',
                triggerAction: 'query',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/queryQuxian"+debug}),
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
            }), new Ext.form.ComboBox({
                hiddenName: 'dstid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属大区',
                triggerAction: 'query',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/queryDaqu"+debug}),
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
                xtype: 'datefield',
                fieldLabel: '项目开始时间',
                editable: false,
                format: 'Y-m-d',
                name: 'startDate',
                width: 160
            }, new Ext.form.ComboBox({
                hiddenName: 'middle',
                mode: 'local',
                width: 160,
                fieldLabel: '是否中途收购',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data: [['y', '是'],
                           ['n', '否']]
                })
            }), {
                xtype: "textarea",
                fieldLabel: "描述",
                name: "desp",
                anchor: "95% 30%"
            }]
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
            fields = ["ctyid", "dstid"];
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

Heat.project.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.project.BasicForm();
        Heat.project.BasicWin.superclass.constructor.call(this, {
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

/** -- 项目分配窗口 -- **/
Heat.project.QueryForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.project.BasicForm.superclass.constructor.call(this, {
            width: 586,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            border: 0,
            items: [{
                layout: 'column',
                items: [{
                    columnWidth:.5,
                    layout: 'form',
                    items: [
                        new Ext.form.ComboBox({
                            hiddenName: 'staff',
                            mode: 'local',
                            width: 140,
                            fieldLabel: '查询员工',
                            triggerAction: 'query',
                            valueField: 'value',
                            displayField: 'text',
                            allowBlank: false,
                            store: new Ext.data.Store({
                                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/queryStaff"+debug}),
                                reader: new Ext.data.ArrayReader({}, [
                                    {name: 'value'},
                                    {name: 'text'}
                                ])
                            })
                        }), {
                            layout:
                            xtype: 'button',
                            text: "点击添加",
                            handler: function(btn) {
                                var form = btn.ownerCt.ownerCt.ownerCt,
                                    basicForm = form.getForm(),
                                    staff = basicForm.findField("staff"),
                                    sid = staff.getValue();
                                if ($(staff.el.dom).hasClass("x-form-invalid") || sid == "") {
                                    Ext.Msg.alert("系统提示", "请选择对应的员工");
                                } else {
                                    Ext.Ajax.request({
                                        url: "/heatManager/data/daqu/project/assignPjt"+debug,
                                        params: {pjtid: form.self.pid, stfid: sid},
                                        success: function(res) {
                                            if (!res) {
                                                Ext.Msg.alert("系统提示", "服务器请求失败");
                                            } else {
                                                var response = Ext.decode(res.responseText);
                                                if (response.success) {
                                                    form.self.refreshStaff(form.self.pid);
                                                } else {
                                                    Ext.Msg.alert("系统提示", response.message);
                                                }
                                            }
                                        },
                                        failure: function() {
                                            Ext.Msg.alert("系统提示", "服务器请求失败");
                                        }
                                    });
                                }
                            }
                        }
                    ]
                }, {
                    columnWidth:.5,
                    layout: 'form',
                    items: [
                        new Ext.form.ComboBox({
                            hiddenName: 'role',
                            mode: 'local',
                            width: 140,
                            fieldLabel: '所属角色',
                            triggerAction: 'query',
                            valueField: 'value',
                            displayField: 'text',
                            allowBlank: false,
                            store: new Ext.data.Store({
                                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/queryRole"+debug}),
                                reader: new Ext.data.ArrayReader({}, [
                                    {name: 'value'},
                                    {name: 'text'}
                                ])
                            })
                        })
                    ]
                }]
            }]
        });
    },

    getStaff: function(pid) {
        this.getForm().findField("staff").getStore().load({params: {pjtid: pid}});
    }
});

Heat.project.EmployeeGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.project.EmployeeGrid.superclass.constructor.call(this, {
            store: new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/relateEmp"+debug}),
                reader: new Ext.data.JsonReader({
                    totalProperty: 'totalProperty',
                    root: 'root',
                    fields: [
                        {name: 'stfid', type: 'int'},
                        {name: 'stfname', type: 'string'},
                        {name: 'stfnumber', type: 'string'}
                    ]
                })
            }),

            columns: [{
                header: "员工编号",
                dataIndex: 'stfid',
                width: 1
            }, {
                header: "员工名字",
                dataIndex: 'stfname',
                width: 3
            }, {
                header: "员工工号",
                dataIndex: 'stfnumber',
                width: 1
            }],

            viewConfig: {
                forceFit: true
            },

            border: 0,
            frame: true,
            loadMask: true,
            collapsible: false,
            width: 586,
            height: 300
        });
    },

    refreshStaff: function(pid) {
        this.getStore().load({params: {pjtid: pid}});
    }
});

Heat.project.EmployeeWin = Ext.extend(Ext.Window, {
    form: null,
    grid: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.project.QueryForm();
        this.form.self = this;
        this.grid = new Heat.project.EmployeeGrid();
        this.grid.self = this;
        Heat.project.EmployeeWin.superclass.constructor.call(this, {
            items: [
                this.form,
                this.grid
            ],

            listeners: {
                'hide': {
                    fn: this.onResetClick,
                    scope: this
                }
            },

            title: '修改记录',
            width: 600,
            buttonAlign: 'center',
            closeAction: 'hide',
            modal: true,
            listeners: {
                show: function(win) {
                    win.form.getStaff(win.pid);
                    win.grid.refreshStaff(win.pid);
                }
            }
        });
    },

    refreshStaff: function() {
        this.grid.refreshStaff();
    },

    onResetClick: function() {
        this.form.reset();
    }
});

Heat.project.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    projectWin: null,
    employeeWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.projectWin = new Heat.project.BasicWin();
        this.employeeWin = new Heat.project.EmployeeWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'pjtid', type: 'int'},
                    {name: 'pjtnum', type: 'string'},
                    {name: 'pjtname', type: 'string'},
                    {name: 'ctyid', type: 'int'},
                    {name: 'townname', type: 'string'},
                    {name: 'dstid', type: 'int'},
                    {name: 'dstname', type: 'string'},
                    {name: 'startDate', type: 'string'},
                    {name: 'middle', type: 'string'},
                    {name: 'status', type: 'string'},
                    {name: 'desp', type: 'string'}
                ]
            })
        });
        Heat.project.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "项目编号",
                dataIndex: 'pjtnum',
                width: 1
            }, {
                header: "项目名称",
                dataIndex: 'pjtname',
                width: 3
            }, {
                header: "所属行政区",
                dataIndex: 'townname',
                width: 1
            }, {
                header: "所属大区",
                dataIndex: 'dstname',
                width: 1
            }, {
                header: "项目开始时间",
                dataIndex: 'startDate',
                width: 1
            }, {
                header: "是否中途收购",
                dataIndex: 'middle',
                width: 1
            }, {
                header: "描述",
                dataIndex: "desp",
                width: 3
            }],

            tbar: [{
                text: "添加项目",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改项目",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除项目",
                iconCls: "del_icon",
                handler: this.onDelClick,
                scope: this
            }, '->', new Ext.form.ComboBox({
                hiddenName: 'dstid',
                mode: 'local',
                width: 100,
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/queryDaqu"+debug+"?query=true"}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                })
            }), {
                text: '查询',
                name: 'search',
                iconCls: 'search',
                handler: this.onSearchClick,
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
                    var store = grid.getStore(),
                        tbar = grid.getTopToolbar(),
                        filters = tbar.findByType("combo");
                    if (grid.dstid) {
                        store.setBaseParam("dstid", grid.dstid);
                        filters[0].setValue(grid.dstname);
                    } else {
                        filters[0].setValue("全部大区");
                    }
                    grid.getStore().load();
                },
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var menu = new Ext.menu.Menu([{
                        text: "分配项目",
                        handler: function() {
                            var store = grid.getStore(),
                                record = store.getAt(rowIndex),
                                pid = record.get("pjtid");
                            grid.employeeWin.pid = pid;
                            grid.employeeWin.show();
                        }
                    }]);
                    menu.showAt(e.getPoint());
                }
            }
        });

        this.projectWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.projectWin.setTitle("新增项目");
        this.projectWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.projectWin.setTitle("修改项目");
            this.projectWin.show();
            this.projectWin.load(selected);
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
        var id = record.get('pjtid');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: "/heatManager/data/daqu/project/del"+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    onSearchClick: function() {
        var data = {params: {}},
            store = this.getStore(),
            tbar = this.getTopToolbar(),
            filters = tbar.findByType("combo"),
            dstid = filters[0].getValue();
        if (dstid == "全部大区") {
            dstid = 0;
        }
        if (store.lastOptions.params) {
            data.params.start = store.lastOptions.params.start;
            data.params.limit = store.lastOptions.params.limit;
        }
        store.setBaseParam("dstid", dstid);
        store.load(data);
    },

    refresh: function() {
        this.projectWin.hide();
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