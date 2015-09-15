<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="元素id"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="元素name"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="元素class"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description=""%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="是否允许编辑"%>
<%@ attribute name="appType" type="java.lang.String" required="true" description="应用类型"%>
<%@ attribute name="msgType" type="java.lang.String" required="false" description="应用支持的消息类型"%>
<%@ attribute name="eventType" type="java.lang.String" required="false" description="应用支持的事件类型"%>
<input id="${id}" name="${name}" type="hidden" value=""/>
<input id="label-${id}" name="label-${name}" ${allowInput?'':'readonly="readonly"'} type="text" value="" class="${cssClass}" style="${cssStyle}"/>
<a id="button-${id}" name="button-search-app" href="javascript:" class="btn btn-xs btn-default">
    &nbsp;<i class="icon-search"></i>&nbsp;
</a>
<script type="text/javascript">
    $(function () {
        <c:if test="${allowInput}">
            $("#label-${id}").blur(function () {
                $("#${id}").val($(this).val());
            });
        </c:if>
        $("#button-${id}").click(function () {
            var url = '${adminPath}/sys/ext/select?appType=${appType}&reqType=${msgType}&eventType=${eventType}';
            app.winModal("选择应用",url, function (retValue) {
                alert(retValue);
            },{
                width:818,
                padding: 0
            });
        });
    });

</script>