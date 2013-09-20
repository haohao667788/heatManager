/**
 * 流水tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.fareflow");

Heat.fareflow.PicWin = Ext.extend(Ext.Window, {
    form: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.form = new Heat.loudong.BasicForm();
        Heat.fareflow.PicWin.superclass.constructor.call(this, {
            title: '原始记录影像',
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

Heat.fareflow.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    picWin: null,
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        this.picWin = new Heat.fareflow.PicWin();
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: "/heatManager/data/fare/fareflow/list"+debug}),
            reader: new Ext.data.JsonReader({
                totalProperty: 'totalProperty',
                root: 'data',
                fields: [
                    {name: 'rcdid', type: 'int'},
                    {name: 'usrid', type: 'int'},
                    {name: 'rcdtime', type: 'string'},
                    {name: 'money', type: 'float'},
                    {name: 'chgtype', type: 'string'},
                    {name: 'checknum', type: 'string'},
                    {name: 'dealname', type: 'string'},
                    {name: 'chgid', type: 'string'},
                    {name: 'rcdpic', type: 'string'},
                    {name: 'stfname', type: 'string'},
                    {name: 'ctfnumber', type: 'string'},
                    {name: 'financechecker', type: 'string'},
                    {name: 'ctfnumber', type: 'string'},
                    {name: 'chargeverifytime', type: 'string'}
                ]
            })
        });
        Heat.fareflow.BasicGrid.superclass.constructor.call(this, {
            store: store,

            columns: [{
                header: "流水号",
                dataIndex: "rcdid",
                width: 2
            }, {
                header: "账号",
                dataIndex: 'ctfnumber',
                width: 2
            }, {
                header: "应缴记录号",
                dataIndex: "chgid",
                width: 4
            }, {
                header: "时间",
                dataIndex: 'rcdtime',
                width: 3
            }, {
                header: "金额",
                dataIndex: "money",
                width: 2
            }, {
                header: "缴费方式",
                dataIndex: "chgtype",
                width: 2
            }, {
                header: "对应号码",
                dataIndex: "checknum",
                width: 3
            }, {
                header: "账期",
                dataIndex: "dealname",
                width: 2
            }, {
                header: "收费员",
                dataIndex: "stfname",
                width: 3
            }, {
                header: "核对员",
                dataIndex: "financechecker",
                width: 3
            }, {
                header: "核对时间",
                dataIndex: "chargeverifytime",
                width: 4
            }],

//            tbar: [{
//                text: "添加流水",
//                iconCls: "add_icon",
//                handler: this.onAddClick,
//                scope: this
//            }, '-', {
//                text: "修改流水",
//                iconCls: "mod_icon",
//                handler: this.onModClick,
//                scope: this
//            }, '-', {
//                text: "删除流水",
//                iconCls: "del_icon",
//                handler: this.onDelClick,
//                scope: this
//            }],

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
                        text: "显示原始记录影像",
                        handler: function() {
                            var record = grid.getStore().getAt(rowIndex),
                                pic = record.get('rcdpic');
                            grid.picWin.pic = pic;
                            grid.picWin.show();
                        }
                    }]);
                    menu.showAt(e.getPoint());
                }
            }
        });
    },

    refresh: function() {
        this.flowWin.hide();
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