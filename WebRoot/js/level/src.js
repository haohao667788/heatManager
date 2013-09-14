/**
 * 热源tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.src");

Heat.src.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.src.BasicForm.superclass.constructor.call(this, {
            url: "/heatManager/data/level/heat/update"+debug,
            width: 300,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                xtype: 'hidden',
                name: 'srcid'
            }, {
                xtype: 'textfield',
                fieldLabel: '热源名称',
                name: 'srcname',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '热源地址',
                name: 'srcaddress',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'dstid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属大区',
                triggerAction: 'query',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/daqu/project/queryDaqu"+debug}),
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
            }), new Ext.form.ComboBox({
                hiddenName: 'heattype',
                mode: 'local',
                fieldLabel: '热源类型',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                width: 160,
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data: [
                        ['居民', '居民'],
                        ['工业', '工业'],
                        ['商业', '商业']
                    ]
                })
            })]
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


Heat.src.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.src.BasicForm();
        Heat.src.BasicWin.superclass.constructor.call(this, {
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


Heat.src.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    srcWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.srcWin = new Heat.src.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/heat/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'srcid', type: 'int'},
                    {name: 'srcname', type: 'string'},
                    {name: 'srcaddress', type: 'string'},
                    {name: 'dstid', type: 'int'},
                    {name: 'dstname', type: 'string'},
                    {name: 'heattype', type: 'string'}
                ]
            })
        });
        Heat.src.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "热源编号",
                dataIndex: 'srcid',
                width: 1
            }, {
                header: "热源名称",
                dataIndex: 'srcname',
                width: 2
            }, {
                header: "热源地址",
                dataIndex: 'srcaddress',
                width: 5
            }, {
                header: "所属大区",
                dataIndex: 'dstname',
                width: 5
            }, {
                header: "供热类型",
                dataIndex: 'heattype',
                width: 2
            }],

            tbar: [{
                text: "添加热源",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改热源",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除热源",
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

        this.srcWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.srcWin.setTitle("新增热源");
        this.srcWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.srcWin.setTitle("修改热源");
            this.srcWin.show();
            this.srcWin.load(selected);
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
                url: "/heatManager/data/level/heat/del"+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.srcWin.hide();
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