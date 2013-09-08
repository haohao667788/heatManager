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
                fieldLabel: '项目名称',
                name: 'pjtname',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'dstid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属大区',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data: [['A', 'A'],
                        ['B', 'B'],
                        ['C', 'C'],
                        ['D', 'D'],
                        ['临修', '临修']]
                })
            }), {
                xtype: 'datefield',
                fieldLabel: '项目开始时间',
                editable: false,
                format: 'Y-m-d',
                name: 'start_date',
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
                anchor: "95% 60%"
            }]
        });

        this.addEvents('submitcomplete');
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


Heat.project.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    projectWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.projectWin = new Heat.project.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'root',
                fields: [
                    {name: 'pjtid', type: 'int'},
                    {name: 'pjtname', type: 'string'},
                    {name: 'dstid', type: 'int'},
                    {name: 'dstname', type: 'string'},
                    {name: 'start_date', type: 'string'},
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
                dataIndex: 'pjtid',
                width: 1
            }, {
                header: "项目名称",
                dataIndex: 'pjtname',
                width: 3
            }, {
                header: "所属大区",
                dataIndex: 'dstname',
                width: 1
            }, {
                header: "项目开始时间",
                dataIndex: 'start_date',
                width: 1
            }, {
                header: "是否中途收购",
                dataIndex: 'middle',
                width: 1
            }, {
                header: "状态",
                dataIndex: 'status',
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
                    // TODO: 判断项目是否已分配
                    var menu = new Ext.menu.Menu([{
                        text: "分配项目",
                        handler: function() {

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
        var id = record.get('id');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: "/heatManager/data/daqu/project/del"+debug,
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