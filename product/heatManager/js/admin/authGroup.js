/**
 * 权限组tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.authGroup");

Heat.authGroup.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.authGroup.BasicForm.superclass.constructor.call(this, {
            width: 500,
            autoHeight: true,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            items: [{
                xtype: 'hidden',
                name: 'authid'
            }, {
                xtype: 'textfield',
                fieldLabel: '权限组名称',
                name: 'authname',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'fieldset',
                title: '模块一',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块二',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块三',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块四',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块五',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块六',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块七',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块八',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
            }, {
                xtype: 'fieldset',
                title: '模块九',
                collapsible: false,
                defaultType: 'checkbox',
                items: [
                    new Ext.form.CheckboxGroup({
                        xtype: 'checkboxgroup',
                        columns: 4,
                        items: [
                            {boxLabel: '功能一', name: 'cb-col-1'},
                            {boxLabel: '功能二', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能三', name: 'cb-col-3'},
                            {boxLabel: '功能四', name: 'cb-col-1'},
                            {boxLabel: '功能五', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能六', name: 'cb-col-3'},
                            {boxLabel: '功能七', name: 'cb-col-1'},
                            {boxLabel: '功能八', name: 'cb-col-2', checked: true},
                            {boxLabel: '功能九', name: 'cb-col-3'}
                        ]
                    })
                ]
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


Heat.authGroup.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.authGroup.BasicForm();
        Heat.authGroup.BasicWin.superclass.constructor.call(this, {
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
            width: 515,
            height: 300,
            autoScroll: true,
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


Heat.authGroup.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    authGroupWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.authGroupWin = new Heat.authGroup.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/authGroup/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'authid', type: 'int'},
                    {name: 'authname', type: 'string'}
                ]
            })
        });
        Heat.authGroup.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "权限组编号",
                dataIndex: 'authid',
                width: 1
            }, {
                header: "权限组名称",
                dataIndex: 'authname',
                width: 5
            }],

            tbar: [{
                text: "添加权限组",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改权限组",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除权限组",
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
                        text: "分配权限",
                        handler: function() {
                        }
                    }]);
                    menu.showAt(e.getPoint());
                }
            }
        });

        this.authGroupWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.authGroupWin.setTitle("新增权限组");
        this.authGroupWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.authGroupWin.setTitle("修改权限组");
            this.authGroupWin.show();
            this.authGroupWin.load(selected);
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
                params: {id: id},
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