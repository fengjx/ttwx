<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ attribute name="name" type="java.lang.String" required="false" description="check名称" %>
<%@ attribute name="values" type="java.util.List" required="false" description="默认选中的值" %>
<%@ attribute name="items" type="java.util.List" required="true" description="选项列表" %>
<%@ attribute name="itemValue" type="java.lang.String" required="true" description="value对应的属性名称" %>
<%@ attribute name="itemLabel" type="java.lang.String" required="true" description="label对应的属性名称" %>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式" %>
<c:set var="isEmpty" value="${empty values}"/>
<c:forEach items="${items}" var="item">
    <c:set var="isCkeck" value="${!isEmpty && fns:contains(values,item[itemValue])}"/>
    ${item[itemLabel]}<input name="${name}" type="checkbox" class="${cssClass}" value="${item[itemValue]}" <c:if test="${isCkeck}">checked="checked"</c:if>/>
</c:forEach>