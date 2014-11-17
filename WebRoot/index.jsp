<%@ page language="java" pageEncoding="UTF-8"%>
<%--<%
	response.setContentType("text/html; charset=gb2312");
	ServletContext sc = getServletContext();
	RequestDispatcher rd = null;
	rd = sc.getRequestDispatcher("/index");      //定向的页面
	rd.forward(request, response);
%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Redirect to index page --%>
<c:redirect url="/index"/>

