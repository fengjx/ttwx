<%@page import="ueditor.ImageManager"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.fengjx.ttwx.modules.common.constants.AppConfig" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%
ServletContext context = request.getSession().getServletContext();  
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
ImageManager imageManager=(ImageManager) ctx.getBean("imageManager");
String imgStr=imageManager.getImagesStr();
	out.print(imgStr);		
%>
<%!

%>