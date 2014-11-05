<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Properties"%>
<%@ page import="ueditor.Uploader"%>
<%@ page import="java.io.FileInputStream"%>


<%
	//request.setCharacterEncoding(Uploader.ENCODEING);
	//response.setCharacterEncoding(Uploader.ENCODEING);
	// 解决中文乱码
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	
	String currentPath = request.getRequestURI().replace(
			request.getContextPath(), "");

	File currentFile = new File(currentPath);

	currentPath = currentFile.getParent() + File.separator;

	//加载配置文件
	String propertiesPath = request.getSession().getServletContext()
			.getRealPath(currentPath + "config.properties");
	Properties properties = new Properties();
	try {
		properties.load(new FileInputStream(propertiesPath));
	} catch (Exception e) {
		//加载失败的处理
		e.printStackTrace();
	}

	Uploader up = new Uploader(request);

	up.setSavePath("static/upload/file/"); //保存路径
	String[] fileType = { ".rar", ".doc", ".docx", ".zip", ".pdf",
			".txt", ".swf", ".wmv", ".avi", ".rm", ".rmvb", ".mpeg",
			".mpg", ".ogg", ".mov", ".wmv", ".mp4" }; //允许的文件类型
	up.setAllowFiles(fileType);
	up.setMaxSize(500 * 1024); //允许的文件最大尺寸，单位KB
	up.myUpload(false);
	
	String resObject = "{'url':'" + up.getUrl() + "','fileType':'" + up.getType() + "','fileName':'" + up.getFileName()+"','title':'" + up.getTitle()
			+ "','state':'" + up.getState() + "','original':'" + up.getOriginalName() + "'}";
	System.out.print("文件上传返回参数："+resObject);
	response.getWriter().print(resObject);
%>
