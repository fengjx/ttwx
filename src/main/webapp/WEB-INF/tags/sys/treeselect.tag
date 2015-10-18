<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）" %>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）" %>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址" %>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true" %>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）" %>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除" %>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写" %>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>
<c:set var="current" value="${fns:currentTimeMillis()}"/>
<input id="${id}" name="${name}" type="hidden" value="${value}"/>
<input id="label${id}" name="label${name}" ${allowInput?'':'readonly="readonly"'} type="text" value=""
       class="${cssClass}" style="${cssStyle}"/>
<a id="btn-search-app${current}" name="btn-search-app" href="javascript:" class="btn btn-xs btn-default">
    &nbsp;<i class="icon-search"></i>&nbsp;
</a>


<div id="modal${current}" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px; height: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择菜单</h4>
            </div>
            <div class="modal-body">
                <div id="tree${current}"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
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
                }
            },
            check: {
                enable: true,
                chkStyle: "checkbox"
            }
        },
        tree: {},
        modal:$('#modal${current}').modal({
            show: false
        }),
        btnSearch:$("#btn-search-app${current}")
    };

    $(document).ready(function () {
        app${current}.btnSearch.click(function () {
            $.ajax({
                url: '${url}',
                cache: false,
                dataType: "json"
            }).done(function (zNodes) {
                app${current}.tree = $.fn.zTree.init($("#tree${current}"), app${current}.setting, zNodes);
                // 默认展开一级节点
                var nodes = app${current}.tree.getNodesByParam("level", 1);
                for(var i=0; i<nodes.length; i++) {
                    app${current}.tree.expandNode(nodes[i], true, false, false);
                }
                selectCheckNode();
                app${current}.modal.modal('show');
            }).fail(function () {
                app.error("加载数据异常");
            });
        });

        // 默认选择节点
        function selectCheckNode(){
            var ids = "${value}".split(",");
            for (var i = 0; i < ids.length; i++) {
                var node = app${current}.tree.getNodeByParam("id", ids[i]);
                if ("${checked}" == "true") {
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