/**
 * 用户登录记录tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.userLoginLog");

Heat.userLoginLog.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.userLoginLog.BasicForm.superclass.constructor.call(this, {
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
                fieldLabel: '记录姓名',
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
                name: 'userLoginLogname',
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


Heat.userLoginLog.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.userLoginLog.BasicForm();
        Heat.userLoginLog.BasicWin.superclass.constructor.call(this, {
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


Heat.userLoginLog.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    userLoginLogWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.userLoginLogWin = new Heat.userLoginLog.BasicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/static/data/employee/userLoginLog/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'root',
                fields: [
                    {name: 'id', type: 'int'},
                    {name: 'userId', type: 'int'},
                    {name: 'userName', type: 'string'},
                    {name: 'loginTime', type: 'string'},
                    {name: 'ip', type: 'string'},
                    {name: 'loginStatus', type: 'string'},
                    {name: 'failReason', type: 'string'},
                    {name: 'duration', type: 'string'}
                ]
            })
        });
        Heat.userLoginLog.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "记录编号",
                dataIndex: 'id',
                width: 1
            }, {
                header: "用户编号",
                dataIndex: 'userId',
                width: 1
            }, {
                header: "用户姓名",
                dataIndex: 'userName',
                width: 1.5
            }, {
                header: "登录时间",
                dataIndex: 'loginTime',
                width: 1.5
            }, {
                header: "登录IP",
                dataIndex: 'ip',
                width: 1.5
            }, {
                header: "登录状态",
                dataIndex: 'loginStatus',
                width: 1
            }, {
                header: "失败原因",
                dataIndex: "failReason",
                width: 2
            }, {
                header: "在线时长",
                dataIndex: "duration",
                width: 1.5
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

        this.userLoginLogWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.userLoginLogWin.setTitle("新增记录");
        this.userLoginLogWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.userLoginLogWin.setTitle("修改记录");
            this.userLoginLogWin.show();
            this.userLoginLogWin.load(selected);
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