/**
 * 菜单界面
 * @author Teddy Bear
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
            text: '区域管理',
            leaf: false,
            children: [{
                text: '员工与任务',
                leaf: false,
                children: [{
                    text: '员工查询',
                    ns: 'employeeInfo',
                    leaf: true
                }, {
                    text: '任务下达',
                    ns: 'task',
                    leaf: true
                }, {
                    text: '账户管理',
                    ns: 'employee',
                    leaf: true
                }, {
                    text: '登录日志',
                    ns: 'userLoginLog',
                    leaf: true
                }]
            }, {
                text: '大区管理',
                leaf: false,
                children: [{
                    text: '收费年度',
                    ns: 'fareYear',
                    leaf: true
                }, {
                    text: '任务分析',
                    ns: 'taskAnalysis',
                    leaf: true
                }, {
                    text: '大区管理',
                    ns: 'daqu',
                    leaf: true
                }, {
                    text: '项目管理',
                    ns: 'project',
                    leaf: true
                }]
            }, {
                text: '分级管理',
                leaf: false,
                children: [{
                    text: '行政区管理',
                    ns: 'quxian',
                    leaf: true
                }, {
                    text: '社区管理',
                    ns: 'shequ',
                    leaf: true
                }, {
                    text: '楼栋管理',
                    ns: 'loudong',
                    leaf: true
                }, {
                    text: '单元管理',
                    ns: 'danyuan',
                    leaf: true
                }, {
                    text: '热源厂管理',
                    ns: 'src',
                    leaf: true
                }, {
                    text: '换热站管理',
                    ns: 'mch',
                    leaf: true
                }]
            }]
        }, {
            text: "收费管理",
            leaf: false,
            children: [{
                text: '收费流水',
                ns: 'fareFlow',
                leaf: true
            }, {
                text: '用户管理',
                ns: 'user',
                leaf: true
            }]
        }, {
            text: "收费工作台",
            leaf: false,
            children: [{
                text: '用户缴费',
                ns: 'userlist',
                leaf: true
            }, {
                text: '收款确认',
                ns: 'fareConfirm',
                leaf: true
            }]
        }, {
            text: '财务工作台',
            leaf: false,
            children: [{
                text: '银行信息管理',
                ns: 'bankInfo',
                leaf: true
            }, {
                text: '财务凭证查询',
                ns: 'financeTicket',
                leaf: true
            }]
        }, {
            text: "系统管理",
            leaf: false,
            children: [{
                text: '权限管理',
                ns: 'authGroup',
                leaf: true
            }, {
                text: '全局用户管理',
                ns: 'administrator',
                leaf: true
            }, {
                text: '数据管理',
                ns: 'dataAdmin',
                leaf: true
            }, {
                text: '系统设置',
                ns: 'system',
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
            items: [new Heat[ns].BasicGrid]
        });
    Heat.tabs.setActiveTab(tab);
});