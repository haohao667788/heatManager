/**
 * tab选项卡
 * @author Teddy Bear
 * @date 2013-08-19
 */
Ext.namespace("Heat.tabs");

Heat.tabs = new Ext.TabPanel({
    activeTab: 0,
    animScroll: true,
    enableTabScroll: true,
    autoScroll: true,
    listeners: {
        contextmenu: function(tabs, self, pos) {
            var menu = Ext.menu.Menu([{
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
            menu.showAt(pos.getPoint());
        }
    }
});