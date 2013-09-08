/**
 * 行政区tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.quxian");

Heat.quxian.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.quxian.BasicForm.superclass.constructor.call(this, {
            url: "/heatManager/data/level/quxian/update"+debug,
            width: 300,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                xtype: 'hidden',
                name: 'ctyid'
            }, {
                xtype: 'textfield',
                fieldLabel: '行政区名称',
                name: 'ctyname',
                width: 160,
                allowBlank: false
            }]
        });

        this.addEvents('submitcomplete');
    },

    setValues: function(record) {
        this.getForm().loadRecord(record);
    },

    //提交表单数据
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

    //当表单提交成功后，触发complete事件(win由于监听了complete事件能通过得到响应)
    submitcomplete: function(form, action) {
        this.fireEvent('submitcomplete');
    }
});


Heat.quxian.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.quxian.BasicForm();
        Heat.quxian.BasicWin.superclass.constructor.call(this, {
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


Heat.quxian.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    quxianWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.quxianWin = new Heat.quxian.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/quxian/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'ctyid', type: 'int'},
                    {name: 'ctyname', type: 'string'},
                    {name: 'cityid', type: 'int'},
                    {name: 'cityname', type: 'string'}
                ]
            })
        });
        Heat.quxian.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "行政区编号",
                dataIndex: 'ctyid',
                width: 1
            }, {
                header: "行政区名称",
                dataIndex: 'ctyname',
                width: 5
            }, {
                header: "所属城市",
                dataIndex: 'cityname',
                width: 2
            }],

            tbar: [{
                text: "添加行政区",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改行政区",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除行政区",
                iconCls: "del_icon",
                handler: this.onDelClick,
                scope: this
            }, '->',
            new Ext.form.ComboBox({
                hiddenName: 'cityid',
                mode: 'local',
                width: 100,
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/quxian/queryCity"+debug+"?query=true"}),
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
                    if (grid.cityid) {
                        store.setBaseParam("cityid", grid.cityid);
                        filters[0].setValue(grid.cityname);
                    } else {
                        filters[0].setValue("全部城市");
                    }
                    grid.getStore().load();
                },
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var menu = new Ext.menu.Menu([{
                        text: "查看所有大区",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                cityid = record.get('cityid'),
                                cityname = record.get('cityname'),
                                ctyid = record.get('ctyid'),
                                ctyname = record.get('ctyname'),
                                newGrid = new Heat.daqu.BasicGrid;

                            newGrid.cityid = cityid;
                            newGrid.cityname = cityname;
                            newGrid.ctyid = ctyid;
                            newGrid.ctyname = ctyname;
                            var tab = Heat.tabs.add({
                                title: "大区管理",
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

        this.quxianWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.quxianWin.setTitle("新增行政区");
        this.quxianWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.quxianWin.setTitle("修改行政区");
            this.quxianWin.show();
            this.quxianWin.load(selected);
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
                url: "/heatManager/data/level/quxian/del"+debug,
                params: {idToDel: id},
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
            cityid = filters[0].getValue();
        if (cityid == "全部城市") {
            cityid = 0;
        }
        if (store.lastOptions.params) {
            data.params.start = store.lastOptions.params.start;
            data.params.limit = store.lastOptions.params.limit;
        }
        store.setBaseParam("cityid", cityid);
        store.load(data);
    },

    refresh: function() {
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