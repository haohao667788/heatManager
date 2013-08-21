/**
 * 主界面
 * @author Teddy Bear
 * @date 2013-08-19
 */
Ext.onReady(function() {
    Ext.BLANK_IMAGE_URL = "js/lib/extjs/resources/images/default/s.gif";
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = "qtip";
    Ext.namespace("Heat.index");

    Heat.index = new Ext.Viewport({
        title: "",
        layout: "border",
        items: [{
            region: "north",
            height: 70,
            html: ""
        }, {
            title: "导航栏",
            region: "west",
            width: 200,
            autoScroll: true,
            collapsible: true,
            split: true,
            items: [Heat.menu]
        }, {
            region: "center",
            items: [Heat.tabs]
        }]
    });
});