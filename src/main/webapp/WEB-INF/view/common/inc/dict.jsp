<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select class="form-control" id="${param.id}" name="${param.name}" style="width: 159px;">
    <c:if test="${param.showAll eq '1'}">
        <option value="" selected="selected">全部</option>
    </c:if>
    <c:forEach var="d" items="${dicts}" varStatus="status">
        <option value="${d.dict_value}">${d.dict_name}</option>
    </c:forEach>
</select>