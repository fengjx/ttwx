<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp"%>
<c:choose>
<c:when test="${!empty param.title }">
<title>${param.title} - <%=appName %></title>
</c:when>
<c:otherwise>
<title><%=appName %></title>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${!empty param.keywords }">
<meta http-equiv="keywords" content="${param.keywords}">
</c:when>
<c:otherwise>
<meta http-equiv="keywords" content="<%=keywords %>">
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${!empty param.description }">
<meta http-equiv="description" content="${param.description}">
</c:when>
<c:otherwise>
<meta http-equiv="description" content="<%=description %>">
</c:otherwise>
</c:choose>
<meta charset="utf-8" />
<meta name="viewport" id="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="shortcut icon" type="image/x-icon" href="<%=resourceUrl%>/img/favicon_wx.ico" />