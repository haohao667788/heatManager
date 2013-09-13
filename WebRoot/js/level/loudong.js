/**
 * 楼栋tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.loudong");

Heat.loudong.BasicForm = Ext.extend(Ext.form.FormPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        Heat.loudong.BasicForm.superclass.constructor.call(this, {
            url: "/heatManager/data/level/loudong/update"+debug,
            width: 500,
            labelAlign: 'right',
            labelWidth: 80,
            frame: true,
            bodyStyle: 'padding: 5px 0 0 0',
            fileUpload: true,
            items: [{
                xtype: 'hidden',
                name: 'bldid'
            }, {
                xtype: 'textfield',
                fieldLabel: '楼栋名称',
                name: 'bldname',
                width: 160,
                allowBlank: false
            }, {
                xtype: 'textfield',
                fieldLabel: '地址',
                name: 'bldaddress',
                width: 160
            }, new Ext.form.ComboBox({
                hiddenName: 'cmtid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属社区',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/loudong/queryShequ"+debug}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                })
            }), new Ext.form.ComboBox({
                hiddenName: 'srcid',
                mode: 'local',
                width: 160,
                fieldLabel: '所属热源',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/loudong/queryHeat"+debug}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                })
            }), new Ext.form.ComboBox({
                hiddenName: 'heattype',
                mode: 'local',
                width: 160,
                fieldLabel: '供热类型',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                allowBlank: false,
                editable: false,
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data: [['A', '居民'],
                        ['B', '工业'],
                        ['C', '商业']]
                })
            }), {
                xtype: 'textfield',
                fieldLabel: 'GIS',
                name: 'gis',
                width: 160
            }, {
                xtype: 'fileuploadfield',
                fieldLabel: '楼栋平面图',
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
        this.fireEvent('submitcomplete');
    }
});


Heat.loudong.BasicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.loudong.BasicForm();
        Heat.loudong.BasicWin.superclass.constructor.call(this, {
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

Heat.loudong.PicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.loudong.BasicForm();
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


Heat.loudong.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    loudongWin: null,
    picWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.loudongWin = new Heat.loudong.BasicWin();
        this.picWin = new Heat.loudong.PicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/loudong/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'bldid', type: 'int'},
                    {name: 'bldname', type: 'string'},
                    {name: 'cmtid', type: 'int'},
                    {name: 'cmtname', type: 'string'},
                    {name: 'pjtid', type: 'int'},
                    {name: 'pjtname', type: 'string'},
                    {name: 'srcid', type: 'int'},
                    {name: 'srcname', type: 'string'},
                    {name: 'heattype', type: 'string'},
                    {name: 'bldaddress', type: 'string'},
                    {name: 'desp', type: 'string'},
                    {name: 'gis', type: 'string'},
                    {name: 'picaddress', type: 'string'}
                ]
            })
        });
        Heat.loudong.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "楼栋编号",
                dataIndex: 'bldid',
                width: 1
            }, {
                header: "楼栋名称",
                dataIndex: 'bldname',
                width: 2
            }, {
                header: "地址",
                dataIndex: 'bldaddress',
                width: 2
            }, {
                header: "所属社区",
                dataIndex: 'cmtname',
                width: 1
            }, {
                header: "所属项目",
                dataIndex: 'pjtname',
                width: 1
            }, {
                header: "所属热源",
                dataIndex: 'srcid',
                width: 1
            }, {
                header: "供热类型",
                dataIndex: 'heattype',
                width: 1
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
                text: "添加楼栋",
                iconCls: "add_icon",
                handler: this.onAddClick,
                scope: this
            }, '-', {
                text: "修改楼栋",
                iconCls: "mod_icon",
                handler: this.onModClick,
                scope: this
            }, '-', {
                text: "删除楼栋",
                iconCls: "del_icon",
                handler: this.onDelClick,
                scope: this
            }, '->', new Ext.form.ComboBox({
                hiddenName: 'cmtid',
                mode: 'local',
                width: 100,
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/loudong/queryShequ"+debug+"?query=true"}),
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
                    if (grid.cmtid) {
                        store.setBaseParam("cmtid", grid.cmtid);
                        filters[0].setValue(grid.cmtname);
                    } else {
                        filters[0].setValue("全部社区");
                    }
                    grid.getStore().load();
                },
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var menu = new Ext.menu.Menu([{
                        text: "查看所有单元",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                cmtid = record.get('cmtid'),
                                cmtname = record.get('cmtname'),
                                bldid = record.get('bldid'),
                                bldname = record.get('bldname'),
                                newGrid = new Heat.danyuan.BasicGrid;

                            newGrid.cmtid = cmtid;
                            newGrid.cmtname = cmtname;
                            newGrid.bldid = bldid;
                            newGrid.bldname = bldname;
                            var tab = Heat.tabs.add({
                                title: "单元管理",
                                //iconCls: 'fwxtabpanelicon',
                                border: 0,
                                autoWidth: true,
                                closable: true,
                                layout: 'fit',
                                items: [newGrid]
                            });
                            Heat.tabs.setActiveTab(tab);

                        }
                    }, {
                        text: "显示楼栋平面图",
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

        this.loudongWin.on("submitcomplete", this.refresh, this);
    },

    onAddClick: function() {
        this.loudongWin.setTitle("新增楼栋");
        this.loudongWin.show();
    },

    onModClick: function() {
        try {
            var selected = this.getSelected();
            this.loudongWin.setTitle("修改楼栋");
            this.loudongWin.show();
            this.loudongWin.load(selected);
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
                url: '/heatManager/level/loudong/del'+debug,
                params: {id: id},
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
            cmtid = filters[0].getValue();
        if (cmtid == "全部社区") {
            cmtid = 0;
        }
        if (store.lastOptions.params) {
            data.params.start = store.lastOptions.params.start;
            data.params.limit = store.lastOptions.params.limit;
        }
        store.setBaseParam("cmtid", cmtid);
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