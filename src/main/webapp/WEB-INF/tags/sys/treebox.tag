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
<%@ attribute name="treeId" type="java.lang.String" required="false" description="数据的id的字段名" %>
<%@ attribute name="treePid" type="java.lang.String" required="false" description="数据的父级id的字段名" %>
<%@ attribute name="treeName" type="java.lang.String" required="false" description="数据显示值的字段名" %>
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
<script type="text/javascript">

    var treebox${current} = {
        input: $("#${id}"),
        label: $("#label${id}"),
        btnSearch: $("#btn-search-app${current}")
    };

    $(document).ready(function () {
        treebox${current}.btnSearch.click(function () {
            top.dialog({
                title: '选择数据',
                url: "${adminPath}/tag/treeselect?url="+encodeURIComponent("${url}")+"&checked=${checked}&treeId=${treeId}&treeName=${treeName}&treePid=${treePid}",
                height: 350,
                width: 250,
                zIndex: app.artZindex,
                onshow: function () {
                },
                oniframeload: function () {
                },
                onclose: function () {
                    if (this.returnValue) {
                        treebox${current}.input.val(this.returnValue.ids);
                        treebox${current}.label.val(this.returnValue.names);
                        treebox${current}.input.attr("data-level", this.returnValue.dataLevel);
                        treebox${current}.input.attr("data-parent-ids", this.returnValue.dataParentIds);
                    }
                    return false;
                },
                button: [
                    <c:if test="${allowClear}">
                    {
                        value: '清除',
                        callback: function () {
                            treebox${current}.input.val("");
                            treebox${current}.input.attr("data-level", "");
                            treebox${current}.input.attr("data-parent-ids", "");
                            treebox${current}.label.val("");
                            return true;
                        }
                    },
                    </c:if>
                    {
                        value: '取消'
                    }
                ]
            }).show(this);
        });
    });

</script>