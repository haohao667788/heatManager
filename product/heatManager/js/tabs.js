/**
 * tab选项卡
 * @author Teddy Bear
 */
Ext.namespace("Heat.tabs");

Heat.tabs = new Ext.TabPanel({
    activeTab: 0,
    border: 0,
    height: document.documentElement.clientHeight-72,
    animScroll: true,
    enableTabScroll: true,
    autoScroll: true,
    listeners: {
        "contextmenu": function(tabs, self, e) {
            var menu = new Ext.menu.Menu([{
                text: "关闭所有选项卡",
                handler: function() {
                    tabs.items.each(function(tab) {
                        tabs.remove(tab);
                    })
                }
            }, {
                text: "关闭其他选项卡",
                handler: function() {
                    tabs.items.each(function(tab) {
                        if (self != tab) {
                            tabs.remove(tab);
                        }
                    })
                }
            }]);
            menu.showAt(e.getPoint());
        }
    }
});

/*
Heat.tabs.add({
    id: 1,
    title: "测试",
    //iconCls: 'fwxtabpanelicon',
    border: 0,
    autoWidth: true,
    closable: true,
    layout: 'fit',
    html: ""
});  */