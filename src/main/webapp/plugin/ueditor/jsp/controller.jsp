<%@ page language="java" contentType="text/html; charset=UTF-8"
		 import="com.fengjx.ttwx.common.ext.baidu.ueditor.ActionEnter" pageEncoding="UTF-8" %>
<%@ page import="com.fengjx.ttwx.modules.common.constants.AppConfig" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");
	response.setHeader("Content-Type", "text/html");
	String rootPath = AppConfig.STATIC_PATH;
	out.write(new ActionEnter(request, rootPath).exec());
%>