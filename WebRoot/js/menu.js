/**
 * 菜单界面
 * @author Teddy Bear
 * @date 2013-08-19
 */
Ext.namespace("Heat.menu");

/*
Heat.menu = new Ext.tree.TreePanel({
    border: false,
    rootVisible: false,
    loader: new Ext.tree.TreeLoader({dataUrl: "test.json"}),
    listeners: {
        'afterrender': function() {

        }
    }
});

var root = new Ext.tree.AsyncTreeNode({
    id: "root",
    text: "根节点"
});
Heat.menu.setRootNode(root);
root.expand(false, false);
Heat.menu.on("click", function(node) {
    alert(node.id);
});
*/

Heat.menu = new Ext.tree.TreePanel({
    border: false,
    rootVisible: false,
    animate: false,
    collapsible: false,
    autoScroll: true,
    loader: new Ext.tree.TreeLoader(),
    root: new Ext.tree.AsyncTreeNode({
        id: 'root',
        text: '根结点',
        expanded: true,
        leaf: false,
        children: [{
            text: '分级信息',
            leaf: false,
            children: [{
                text: '区县信息',
                ns: 'quxian',
                leaf: true
            }, {
                text: '大区信息',
                ns: 'daqu',
                leaf: true
            }, {
                text: '项目信息',
                ns: 'project',
                leaf: true
            }, {
                text: '社区信息',
                ns: 'shequ',
                leaf: true
            }, {
                text: '楼栋信息',
                ns: 'loudong',
                leaf: true
            }, {
                text: '单元信息',
                ns: 'danyuan',
                leaf: true
            }]
        }]
    })
});

Heat.menu.on("click", function(node) {
    var ns = node.attributes.ns,
        tab = Heat.tabs.add({
            title: node.text,
            //iconCls: 'fwxtabpanelicon',
            border: 0,
            autoWidth: true,
            closable: true,
            layout: 'fit',
            items: [new Heat.quxian.BasicGrid]
        });
    Heat.tabs.setActiveTab(tab);
});