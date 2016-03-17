<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ attribute name="value" type="java.lang.String" required="false" description="默认选中的值" %>
<%@ attribute name="items" type="java.util.List" required="true" description="选项列表" %>
<%@ attribute name="itemValue" type="java.lang.String" required="true" description="value对应的属性名称" %>
<%@ attribute name="itemLabel" type="java.lang.String" required="true" description="label对应的属性名称" %>
<c:forEach items="${items}" var="item">
    <option value="${item[itemValue]}" <c:if test="${value eq item[itemValue]}">selected="selected"</c:if>>${item[itemLabel]}</option>
</c:forEach>