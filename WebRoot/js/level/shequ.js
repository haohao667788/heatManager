/**
 * 社区tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.shequ");

Heat.shequ.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.shequ.BasicForm.superclass.constructor.call(this, {
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'id'
            }, {
                xtype: 'textfield',
                fieldLabel: '社区名称',
                name: 'name',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'projectId',
                mode: 'local',
                width: 160,
                fieldLabel: '所属项目',
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
                xtype: 'textfield',
                fieldLabel: '简称',
                name: 'shortName',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: '地址',
                name: 'addr',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: 'GIS坐标',
                name: 'GIS',
                width: 160
            }, {
                xtype: 'fileuploadfield',
                fieldLabel: '社区平面图',
                name: 'chart',
                width: 160,
                buttonText: '',
                buttonCfg: {
                    iconCls: 'upload_icon'
                }
            }, {
                xtype: "textarea",
                fieldLabel: "描述",
                name: "desc",
                anchor: "95% 40%"
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


Heat.shequ.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.shequ.BasicForm();
        Heat.shequ.BasicWin.superclass.constructor.call(this, {
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


Heat.shequ.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    shequWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.shequWin = new Heat.shequ.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: ""}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'root',
                fields: [
                    {name: 'id', type: 'int'},
                    {name: 'name', type: 'string'},
                    {name: 'projectId', type: 'int'},
                    {name: 'project', type: 'string'},
                    {name: 'shortName', type: 'string'},
                    {name: 'addr', type: 'string'},
                    {name: 'desc', type: 'string'},
                    {name: 'GIS', type: 'string'},
                    {name: 'chart', type: 'string'}
                ]
            })
        });
        Heat.shequ.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "社区名称",
                dataIndex: 'name'
            }, {
                header: "所属项目",
                dataIndex: 'project'
            }, {
                header: "简称",
                dataIndex: 'shortName'
            }, {
                header: "地址",
                dataIndex: 'addr'
            }, {
                header: "描述",
                dataIndex: "desc"
            }, {
                header: "GIS坐标",
                dataIndex: "GIS"
            }],

            tbar: [{
                text: "添加社区",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改社区",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除社区",
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
            collapsible: false
        });

        this.shequWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.shequWin.setTitle("新增社区");
        this.shequWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.shequWin.setTitle("修改社区");
            this.shequWin.show();
            this.shequWin.load(selected);
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