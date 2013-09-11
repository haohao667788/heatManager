/**
 * 员工tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.employee");

Heat.employee.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.employee.BasicForm.superclass.constructor.call(this, {
            width: 300,
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
                fieldLabel: '员工姓名',
                name: 'name',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '员工工号',
                name: 'employeeId',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '联系方式',
                name: 'info',
                width: 160,
                allowBlank: false
            }, new Ext.form.ComboBox({
                hiddenName: 'department',
                mode: 'local',
                width: 160,
                fieldLabel: '所属部门',
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
                fieldLabel: '登录名',
                name: 'employeename',
                width: 160
            }, {
                xtype: 'textfield',
                inputType: 'password',
                fieldLabel: '密码',
                name: 'password',
                width: 160
            }, new Ext.form.ComboBox({
                hiddenName: 'authMethod',
                mode: 'local',
                width: 160,
                fieldLabel: '鉴权方式',
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
            }), new Ext.form.ComboBox({
                hiddenName: 'groupId',
                mode: 'local',
                width: 160,
                fieldLabel: '所属权限组',
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
            }), new Ext.form.ComboBox({
                hiddenName: 'proListId',
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
            })]
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


Heat.employee.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.employee.BasicForm();
        Heat.employee.BasicWin.superclass.constructor.call(this, {
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


Heat.employee.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    employeeWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.employeeWin = new Heat.employee.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: ""}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'root',
                fields: [
                    {name: 'id', type: 'int'},
                    {name: 'name', type: 'string'},
                    {name: 'employeeId', type: 'int'},
                    {name: 'info', type: 'string'},
                    {name: 'departmentId', type: 'int'},
                    {name: 'department', type: 'string'},
                    {name: 'employeename', type: 'string'},
                    {name: 'password', type: 'string'},
                    {name: 'authMethod', type: 'string'},
                    {name: 'groupId', type: 'init'},
                    {name: 'group', type: 'string'},
                    {name: 'proListId', type: 'string'},
                    {name: 'proList', type: 'string'},
                    {name: 'createTime', type: 'string'},
                    {name: 'lastLoginTime', type: 'string'},
                    {name: 'lastDuration', type: 'string'},
                    {name: 'credit', type: 'int'}
                ]
            })
        });
        Heat.employee.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "员工编号",
                dataIndex: 'id',
                width: 80
            }, {
                header: "员工姓名",
                dataIndex: 'name',
                width: 100
            }, {
                header: "员工工号",
                dataIndex: 'employeeId',
                width: 80
            }, {
                header: "联系方式",
                dataIndex: 'info',
                width: 160
            }, {
                header: "所属部门",
                dataIndex: 'department',
                width: 160
            }, {
                header: "登录名",
                dataIndex: 'employeename',
                width: 160
            }, {
                header: "密码",
                dataIndex: "password",
                width: 160
            }, {
                header: "鉴权方式",
                dataIndex: "authMethod",
                width: 160
            }, {
                header: "所属权限组",
                dataIndex: "group",
                width: 160
            }, {
                header: "所属项目",
                dataIndex: "proList",
                width: 160
            }, {
                header: "添加时间",
                dataIndex: "createTime",
                width: 120
            }, {
                header: "上次登录时间",
                dataIndex: 'lastLoginTime',
                width: 160
            }, {
                header: "上次登录时长",
                dataIndex: 'lastDuration',
                width: 160
            }, {
                header: "登录积分",
                dataIndex: 'credit',
                width: 160
            }],

            tbar: [{
                text: "添加员工",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改员工",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除员工",
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
                forceFit: false
            },

            frame: true,
            loadMask: true,
            collapsible: false
        });

        this.employeeWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.employeeWin.setTitle("新增员工");
        this.employeeWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.employeeWin.setTitle("修改员工");
            this.employeeWin.show();
            this.employeeWin.load(selected);
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