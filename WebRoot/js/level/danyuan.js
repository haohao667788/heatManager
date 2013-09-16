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
            url: '/heatManager/data/level/danyuan/update'+debug,
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
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/danyuan/queryLoudong"+debug}),
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
                fieldLabel: 'GIS',
                name: 'gis',
                width: 160,
                allowBlank: false
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
        var isInvalid = false,
            form = this.getForm(),
            fields = ["bldid"];
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
        this.reset();
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

Heat.danyuan.PicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.danyuan.BasicForm();
        Heat.loudong.PicWin.superclass.constructor.call(this, {
            title: '楼层平面图',
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

Heat.danyuan.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    danyuanWin: null,
    picWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.danyuanWin = new Heat.danyuan.BasicWin();
        this.picWin = new Heat.loudong.PicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/danyuan/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'untid', type: 'int'},
                    {name: 'untname', type: 'string'},
                    {name: 'bldid', type: 'int'},
                    {name: 'bldname', type: 'string'},
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
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/danyuan/queryShequ"+debug+"?query=true"}),
                    reader: new Ext.data.ArrayReader({}, [
                        {name: 'value'},
                        {name: 'text'}
                    ])
                }),
                listeners: {
                    select: function(combo, value) {
                        combo.nextSibling.setValue("");
                        combo.nextSibling.getStore().load({params: {cmtid: value}});
                    }
                }
            }), new Ext.form.ComboBox({
                hiddenName: 'bldid',
                mode: 'local',
                width: 100,
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                editable: false,
                store: new Ext.data.Store({
                    autoLoad: true,
                    proxy: new Ext.data.HttpProxy({url: "/heatManager/data/level/danyuan/queryLoudong"+debug+"?query=true"}),
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
                render: this.onShow,
                rowcontextmenu: function(grid, rowIndex, e) {
                    e.preventDefault();
                    if (rowIndex < 0) return;
                    var menu = new Ext.menu.Menu([{
                        text: "显示楼层平面图",
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

        this.danyuanWin.on("submitcomplete", this.refresh, this);
    },

    onShow: function() {
        var grid = this,
            store = grid.getStore(),
            tbar = grid.getTopToolbar(),
            filters = tbar.findByType("combo");
        if (grid.cmtid && grid.bldid) {
            store.setBaseParam("cmitd", grid.cmtid);
            store.setBaseParam("bldtd", grid.bldid);
            filters[0].setValue(grid.cmtname);
            filters[1].setValue(grid.bldname);
        } else {
            filters[0].setValue("全部社区");
            filters[1].setValue("全部楼栋");
        }
        grid.getStore().load();
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
        var id = record.get('untid');
        if(btn == 'yes') {
            Ext.Ajax.request({
                url: '/heatManager/data/level/danyuan/del'+debug,
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
            cmtid = filters[0].getValue(),
            bldid = filters[1].getValue();
        if (bldid == "") {
            Ext.Msg.alert("系统提示", "请选择楼栋");
        }

        if (cmtid == "全部社区") {
            cmtid = 0;
        }
        if (bldid == "全部楼栋") {
            bldid = 0;
        }
        if (store.lastOptions.params) {
            data.params.start = store.lastOptions.params.start;
            data.params.limit = store.lastOptions.params.limit;
        }
        store.setBaseParam("cmtid", cmtid);
        store.setBaseParam("bldid", bldid);
        store.load(data);
    },

    refresh: function() {
        this.danyuanWin.hide();
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