<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）" %>
<%@ attribute name="values" type="java.util.List" required="false" description="隐藏域值（ID）" %>
<%@ attribute name="checked" type="java.lang.String" required="false" description="选中的值" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>
<div class="radio">
    <c:forEach items="${values}" var="item">
        <label>
            <input name="${name}" value="${item.value}" <c:if test="${not empty checked && checked eq item.value}">checked="checked"</c:if> type="radio" class="ace" style="${cssStyle}" />
            <span class="lbl">${item.label}</span>
        </label>
    </c:forEach>
</div>
