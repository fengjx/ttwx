<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）" %>
<%@ attribute name="value" type="java.lang.String" required="false" description="隐藏域值（ID）" %>
<%@ attribute name="labelValue" type="java.lang.String" required="false" description="value对应显示的值" %>
<%@ attribute name="treeLevel" type="java.lang.String" required="false" description="菜单级别" %>
<%@ attribute name="parentIds" type="java.lang.String" required="false" description="所以父级ID" %>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址" %>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框（多选情况显示）" %>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）" %>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除" %>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写" %>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>
<c:set var="current" value="${fns:currentTimeMillis()}"/>
<input id="${id}" name="${name}" type="hidden" value="${value}" data-level="${treeLevel}"
       data-parent-ids="${parentIds}"/>
<input id="label${id}" name="label${name}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}"
       class="${cssClass}" style="${cssStyle}"/>
<a id="btn-search-app${current}" name="btn-search-app" href="javascript:" class="btn btn-xs btn-default">
    &nbsp;<i class="icon-search"></i>&nbsp;
</a>

<div id="modal${current}" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 300px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择菜单</h4>
            </div>
            <div class="modal-body" style="height: 300px;">
                <div>
                    <ul id="tree${current}" class="ztree"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <c:if test="${allowClear == true}">
                    <button id="btn-clean${current}" type="button" class="btn btn-default btn-sm" data-dismiss="modal">
                        清除
                    </button>
                </c:if>
                <button id="btn-ok${current}" type="button" class="btn btn-primary btn-sm">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">

    var app${current} = {
        setting: {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parent_id",
                    rootPId: null
                },
                key: {
                    url: "xUrl"
                }
            },
            check: {
                enable: ${checked?true:false},
                chkStyle: "checkbox"
            },
            callback: {
                onDblClick: function (event, treeId, node) {
                    app${current}.setVal(node);
                }
            }
        },
        tree: {},
        modal: $('#modal${current}').modal({
            show: false
        }),
        btnSearch: $("#btn-search-app${current}"),
        btnClean: $("#btn-clean${current}"),
        btnOk: $("#btn-ok${current}"),
        input: $("#${id}"),
        label: $("#label${id}"),
        setVal: function (nodes) {  // 赋值

            var ids = [], names = [];
            // 多选
            if (app${current}.setting.check.enable && nodes instanceof Array) {
                for (var i = 0; i < nodes.length; i++) {
                    ids.push(nodes[i].id);
                    names.push(nodes[i].name);
                }
            } else {
                var n = (nodes instanceof Array) ? nodes[0] : nodes;
                ids.push(n.id);
                names.push(n.name);
                app${current}.input.attr("data-level", parseInt(n.level) + 1);
                app${current}.input.attr("data-parent-ids", n.id);
                if (n.parent_id && '' !== n.parent_id) {
                    app${current}.setParent(n.getParentNode());
                }
            }
            app${current}.input.val(ids.join(","));
            app${current}.label.val(names.join(","));
            app${current}.modal.modal('hide');
        },
        setParent: function (pNode) {
            var parentIds = app${current}.input.attr("data-parent-ids");
            app${current}.input.attr("data-parent-ids", pNode.id + "," + parentIds);
            if (pNode.parent_id && '' !== pNode.parent_id) {
                app${current}.setParent(pNode.getParentNode());
            }
        }
    };

    $(document).ready(function () {
        app${current}.btnClean.click(function () {
            app${current}.input.val("");
            app${current}.input.attr("data-level", "");
            app${current}.label.val("");
            app${current}.modal.modal('hide');
        });

        app${current}.btnOk.click(function () {
            app${current}.setVal(app${current}.tree.getSelectedNodes());
        });

        app${current}.btnSearch.click(function () {
            $.ajax({
                url: '${url}',
                cache: false,
                dataType: "json"
            }).done(function (zNodes) {
                app${current}.tree = $.fn.zTree.init($("#tree${current}"), app${current}.setting, zNodes);
                // 默认展开一级节点
                var nodes = app${current}.tree.getNodesByParam("level", 0);
                for (var i = 0; i < nodes.length; i++) {
                    app${current}.tree.expandNode(nodes[i], true, false, false);
                }
                selectCheckNode();
                app.closeDialog();
                app${current}.modal.modal('show');
            }).fail(function () {
                app.error("加载数据异常");
            });
        });

        // 默认选择节点
        function selectCheckNode() {
            var ids = "${value}".split(",");
            for (var i = 0; i < ids.length; i++) {
                var node = app${current}.tree.getNodeByParam("id", ids[i]);
                if (app${current}.setting.check.enable) {
                    try {
                        app${current}.tree.checkNode(node, true, true);
                    } catch (e) {
                    }
                    app${current}.tree.selectNode(node, false);
                } else {
                    app${current}.tree.selectNode(node, true);
                }
            }
        }

    });

</script>