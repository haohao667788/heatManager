/**
 * 机组tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.mch");

Heat.mch.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.mch.BasicForm.superclass.constructor.call(this, {
            url: "/heatManager/data/level/mch/update"+debug,
            width: 300,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                xtype: 'hidden',
                name: 'mchid'
            }, {
                xtype: 'textfield',
                fieldLabel: '机组名称',
                name: 'mchname',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'srcid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属热源',
                triggerAction: 'query',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/mch/querySrc"+debug}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                }),
                listeners: {
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
                fieldLabel: 'GIS坐标',
                name: 'gis',
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
        var isInvalid = false,
            form = this.getForm(),
            fields = ["srcid"];
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
        this.fireEvent('submitcomplete');
    }
});


Heat.mch.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.mch.BasicForm();
        Heat.mch.BasicWin.superclass.constructor.call(this, {
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


Heat.mch.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    mchWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.mchWin = new Heat.mch.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/mch/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'mchid', type: 'int'},
                    {name: 'mchname', type: 'string'},
                    {name: 'srcid', type: 'int'},
                    {name: 'srcname', type: 'string'},
                    {name: 'gis', type: 'string'}
                ]
            })
        });
        Heat.mch.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "机组编号",
                dataIndex: 'mchid',
                width: 1
            }, {
                header: "机组名称",
                dataIndex: 'mchname',
                width: 2
            }, {
                header: "所属热源",
                dataIndex: 'srcname',
                width: 3
            }, {
                header: "机组GIS坐标",
                dataIndex: 'gis',
                width: 2
            }],

            tbar: [{
                text: "添加机组",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改机组",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除机组",
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

        this.mchWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.mchWin.setTitle("新增机组");
        this.mchWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.mchWin.setTitle("修改机组");
            this.mchWin.show();
            this.mchWin.load(selected);
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
        var id = record.get('mchid');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: "/heatManager/data/level/mch/del"+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.mchWin.hide();
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