<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp"%>
<%@ attribute name="infoMsg" type="java.lang.String" required="false" description="消息内容"%>
<%@ attribute name="errorMsg" type="java.lang.String" required="false" description="消息内容"%>
<c:choose>
	<c:when test="${not empty infoMsg}">
		<script type="text/javascript">
			app.ok('${infoMsg}');
		</script>
	</c:when>
	<c:when test="${not empty errorMsg}">
		<script type="text/javascript">
			app.error('${infoMsg}');
		</script>
	</c:when>
</c:choose>