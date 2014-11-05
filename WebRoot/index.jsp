<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	response.setContentType("text/html; charset=gb2312");
	ServletContext sc = getServletContext();
	RequestDispatcher rd = null;
	rd = sc.getRequestDispatcher("/index");      //定向的页面
	rd.forward(request, response);
%>
