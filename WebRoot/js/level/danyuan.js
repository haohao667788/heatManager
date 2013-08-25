/**
 * 单元tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.danyuan");

Heat.danyuan.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.danyuan.BasicForm.superclass.constructor.call(this, {
            url: '/data/level/danyuan/list.json',
            width: 300,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'untid'
            }, {
                xtype: 'textfield',
                fieldLabel: '单元名称',
                name: 'untname',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'bldid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属楼栋',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/data/level/danyuan/queryLoudong.json"}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                })
            }), new Ext.form.ComboBox({
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
                    proxy: new Ext.data.HttpProxy({url: "/data/level/danyuan/queryDanyuan.json"}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                })
            }), {
                xtype: 'textfield',
                fieldLabel: 'GIS坐标',
                name: 'gis',
                width: 160
            }, {
                xtype: 'fileuploadfield',
                fieldLabel: '单元平面图',
                name: 'picaddress',
                width: 160,
                buttonText: '',
                buttonCfg: {
                    iconCls: 'upload_icon'
                }
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


Heat.danyuan.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.danyuan.BasicForm();
        Heat.danyuan.BasicWin.superclass.constructor.call(this, {
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


Heat.danyuan.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    danyuanWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.danyuanWin = new Heat.danyuan.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/data/level/danyuan/list.json"}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'untid', type: 'int'},
                    {name: 'untname', type: 'string'},
                    {name: 'bldid', type: 'int'},
                    {name: 'bldname', type: 'string'},
                    {name: 'pjtid', type: 'int'},
                    {name: 'pjtname', type: 'string'},
                    {name: 'mchid', type: 'int'},
                    {name: 'mchname', type: 'string'},
                    {name: 'gis', type: 'string'},
                    {name: 'picaddress', type: 'string'}
                ]
            })
        });
        Heat.danyuan.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "单元编号",
                dataIndex: 'untid',
                width: 1
            }, {
                header: "单元名称",
                dataIndex: 'untname',
                width: 2
            }, {
                header: "所属楼栋",
                dataIndex: 'bldname',
                width: 2
            }, {
                header: "所属项目",
                dataIndex: 'pjtname',
                width: 1
            }, {
                header: "所属机组",
                dataIndex: 'mchname',
                width: 1
            }, {
                header: "GIS坐标",
                dataIndex: "gis",
                width: 1
            }],

            tbar: [{
                text: "添加单元",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改单元",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除单元",
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

        this.danyuanWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.danyuanWin.setTitle("新增单元");
        this.danyuanWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.danyuanWin.setTitle("修改单元");
            this.danyuanWin.show();
            this.danyuanWin.load(selected);
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
                url: '',
                params: {idToDel: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
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