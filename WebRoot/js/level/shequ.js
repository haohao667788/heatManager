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
            url: '/heatManager/data/level/shequ/update'+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'cmtid'
            }, {
                xtype: 'textfield',
                fieldLabel: '社区名称',
                name: 'cmtname',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '简称',
                name: 'briefname',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: '地址',
                name: 'cmtaddress',
                width: 160
            }, {
                xtype: 'textfield',
                fieldLabel: 'GIS坐标',
                name: 'gis',
                width: 160
            }, {
                xtype: 'fileuploadfield',
                fieldLabel: '社区平面图',
                name: 'picaddress',
                width: 160,
                buttonText: '',
                buttonCfg: {
                    iconCls: 'upload_icon'
                }
            }, {
                xtype: "textarea",
                fieldLabel: "描述",
                name: "desp",
                anchor: "95% 40%"
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
        this.reset();
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

Heat.shequ.PicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.shequ.BasicForm();
        Heat.loudong.PicWin.superclass.constructor.call(this, {
            title: '楼栋平面图',
            width: 800,
            height: 400,
            autoScroll: true,
            closeAction: 'hide',
            modal: true,
            html: '<img src="image/loading.gif">',
            listeners: {
                show: function(win) {
                    win.el.dom.getElementsByTagName("img")[0].src = win.pic;
                }
            }
        });
    }
});

Heat.shequ.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    shequWin: null,
    picWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.shequWin = new Heat.shequ.BasicWin();
        this.picWin = new Heat.shequ.PicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/shequ/getlist"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'cmtid', type: 'int'},
                    {name: 'cmtname', type: 'string'},
                    {name: 'briefname', type: 'string'},
                    {name: 'cmtaddress', type: 'string'},
                    {name: 'desp', type: 'string'},
                    {name: 'gis', type: 'string'},
                    {name: 'picaddress', type: 'string'}
                ]
            })
        });
        Heat.shequ.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "社区编号",
                dataIndex: 'cmtid',
                width: 1
            }, {
                header: "社区名称",
                dataIndex: 'cmtname',
                width: 2
            }, {
                header: "简称",
                dataIndex: 'briefname',
                width: 1
            }, {
                header: "地址",
                dataIndex: 'cmtaddress',
                width: 2
            }, {
                header: "描述",
                dataIndex: "desp",
                width: 2
            }, {
                header: "GIS坐标",
                dataIndex: "gis",
                width: 1
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
            collapsible: false,
            listeners: {
                render: function(grid) {
                    grid.getStore().load();
                },
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var menu = new Ext.menu.Menu([{
                        text: "查看所有楼栋",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                cmtid = record.get('cmtid'),
                                cmtname = record.get('cmtname'),
                                newGrid = new Heat.loudong.BasicGrid,
                                ns = "loudong",
                                o_tab = Heat.tabs.getComponent(ns);

                            if (!o_tab) {
                                newGrid.cmtid = cmtid;
                                newGrid.cmtname = cmtname;
                                var tab = Heat.tabs.add({
                                    id: ns,
                                    title: "楼栋管理",
                                    //iconCls: 'fwxtabpanelicon',
                                    border: 0,
                                    autoWidth: true,
                                    closable: true,
                                    layout: 'fit',
                                    items: [newGrid]
                                });
                                Heat.tabs.setActiveTab(tab);
                            } else {
                                var g = o_tab.items.items[0];
                                g.cmtid = cmtid;
                                g.cmtname = cmtname;
                                g.onShow();
                                Heat.tabs.setActiveTab(ns);
                            }
                        }
                    }, {
                        text: "显示社区平面图",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                pic = record.get('picaddress');
                            grid.picWin.pic = pic;
                            grid.picWin.show();
                        }
                    }]);
                    menu.showAt(e.getPoint());
                }
            }
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
        var id = record.get('cmtid');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: '/heatManager/data/level/shequ/del'+debug,
                params: {id: id},
                success: function(response) {
                    store.reload();
                }
            })
        }
    },

    refresh: function() {
        this.shequWin.hide();
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