/**
 * 用户登录记录tab
 * @author Teddy Bear
 */
Ext.namespace("Heat.userLoginLog");

Heat.userLoginLog.BasicGrid = Ext.extend(Ext.grid.GridPanel, {
    constructor: function(cfg) {
        cfg = cfg || {};
        Ext.apply(this, cfg);
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({url: ""}),
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
    },

    getSelected: function() {
        var sm = this.getSelectionModel();
        if (sm.getCount() == 0) {
            throw new Error('请先选择一条记录');
        }
        return sm.getSelected();
    }
});