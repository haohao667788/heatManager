/**
 * 大区tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.daqu");

Heat.daqu.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.daqu.BasicForm.superclass.constructor.call(this, {
            url: '/heatManager/data/daqu/daqu/update'+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                xtype: 'hidden',
                name: 'dstid'
            }, {
                xtype: 'textfield',
                fieldLabel: '大区名称',
                name: 'dstname',
                width: 160,
                allowBlank: false
            }, {
                xtype: "textarea",
                fieldLabel: "描述",
                name: "desp",
                anchor: "95% 60%"
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


Heat.daqu.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.daqu.BasicForm();
        Heat.daqu.BasicWin.superclass.constructor.call(this, {
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


Heat.daqu.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    daquWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.daquWin = new Heat.daqu.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/daqu/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'dstid', type: 'int'},
                    {name: 'dstname', type: 'string'},
                    {name: 'desp', type: 'string'}
                ]
            })
        });
        Heat.daqu.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "大区编号",
                dataIndex: "dstid",
                width: 1
            }, {
                header: "大区名称",
                dataIndex: 'dstname',
                width: 4
            }, {
                header: "描述",
                dataIndex: "desp",
                width: 4
            }],

            tbar: [{
                text: "添加大区",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改大区",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除大区",
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
                    var menu = new Ext.menu.Menu([{
                        text: "查看所有项目",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                dstid = record.get('dstid'),
                                dstname = record.get('dstname'),
                                newGrid = new Heat.project.BasicGrid;

                            newGrid.dstid = dstid;
                            newGrid.dstname = dstname;
                            var tab = Heat.tabs.add({
                                title: "项目管理",
                                //iconCls: 'fwxtabpanelicon',
                                border: 0,
                                autoWidth: true,
                                closable: true,
                                layout: 'fit',
                                items: [newGrid]
                            });
                            Heat.tabs.setActiveTab(tab);

                        }
                    }]);
                    menu.showAt(e.getPoint());
                }
            }
        });

        this.daquWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.daquWin.setTitle("新增大区");
        this.daquWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.daquWin.setTitle("修改大区");
            this.daquWin.show();
            this.daquWin.load(selected);
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
                url: "/heatManager/data/daqu/daqu/del"+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.daquWin.hide();
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