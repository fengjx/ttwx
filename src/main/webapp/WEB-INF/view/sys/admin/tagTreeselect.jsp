<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="blank"/>
    <title>数据选择</title>
</head>
<body>
<div style="height: 350px; overflow-y:auto;">
    <ul id="treeData" class="ztree"></ul>
</div>
<script type="text/javascript">
    var appTree = {
        setting: {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "${empty treeId?'id':treeId}",
                    pIdKey: "${empty treePid?'parent_id':treePid}",
                    rootPId: null
                },
                key: {
                    name: "${empty treeName?'name':treeName}",
                    url: "xUrl"
                }
            },
            check: {
                enable: ${checked?true:false},
                chkStyle: "checkbox"
            },
            callback: {
                onDblClick: function (event, treeId, node) {
                    appTree.setVal(node);
                }
            }
        },
        tree: {},
        btnClean: $("#btn-clean"),
        btnOk: $("#btn-ok"),
        input: $("#${id}"),
        label: $("#label${id}"),
        setVal: function (nodes) {  // 赋值
            var data = {
                ids: "",
                names: "",
                dataLevel: "",
                dataParentIds: ""
            };
            var ids = [], names = [];
            // 多选
            if (appTree.setting.check.enable && nodes instanceof Array) {
                for (var i = 0; i < nodes.length; i++) {
                    ids.push(nodes[i].id);
                    names.push(nodes[i].name);
                }
            } else {
                var n = (nodes instanceof Array) ? nodes[0] : nodes;
                ids.push(n.id);
                names.push(n.name);
                data.dataLevel = parseInt(n.level) + 1;
                data.dataParentIds = n.id;
                if (n.parent_id && '' !== n.parent_id) {
                    appTree.setParent(n.getParentNode(), data);
                }
            }
            data.ids = ids.join(",");
            data.names = names.join(",");
            // 关闭（隐藏）对话框
            appTree.dialog.close(data);
            // 主动销毁对话框
            appTree.dialog.remove();
        },
        setParent: function (pNode, data) {
            data.dataParentIds = pNode.id + "," + data.dataParentIds;
            if (pNode.parent_id && '' !== pNode.parent_id) {
                appTree.setParent(pNode.getParentNode(), data);
            }
        },
        dialog: top.dialog.get(window)
    };

    $(document).ready(function () {
        $.ajax({
            url: '${url}',
            cache: false,
            dataType: "json"
        }).done(function (zNodes) {
            appTree.tree = $.fn.zTree.init($("#treeData"), appTree.setting, zNodes);
            // 默认展开一级节点
            var nodes = appTree.tree.getNodesByParam("level", 0);
            for (var i = 0; i < nodes.length; i++) {
                appTree.tree.expandNode(nodes[i], true, false, false);
            }
            selectCheckNode();
        }).fail(function () {
            app.error("加载数据异常");
        });
        appTree.btnClean.click(function () {
            appTree.input.val("");
            appTree.input.attr("data-level", "");
            appTree.label.val("");
        });

        appTree.btnOk.click(function () {
            appTree.setVal(appTree.tree.getSelectedNodes());
        });

        // 默认选择节点
        function selectCheckNode() {
            var ids = "${value}".split(",");
            for (var i = 0; i < ids.length; i++) {
                var node = appTree.tree.getNodeByParam("id", ids[i]);
                if (appTree.setting.check.enable) {
                    try {
                        appTree.tree.checkNode(node, true, true);
                    } catch (e) {
                    }
                    appTree.tree.selectNode(node, false);
                } else {
                    appTree.tree.selectNode(node, true);
                }
            }
        }
    });

</script>
</body>
</html>
