<%@page import="com.fjx.wechat.config.AppConfig"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<% 
    //仅做示例用，请自行修改
	String path = "/upload/images/";
	String imgStr ="";
	//String realpath = getRealPath(request,path)+"/"+path;
	String realpath = AppConfig.STATIC_PATH + path;
	System.out.println(realpath);
	List<File> files = getFiles(realpath,new ArrayList());
	for(File file :files ){
		//imgStr += file.getPath().replace(getRealPath(request,path),"")+"ue_separate_ue";
		imgStr += file.getPath().replace("\\", "/").replace(AppConfig.STATIC_PATH,"")+"ue_separate_ue";
		//System.out.println(imgStr);
	}
	if(imgStr!=""){
        imgStr = imgStr.substring(0,imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
    }
	//System.out.println(imgStr);
	out.print(imgStr);		
%>
<%!
public List getFiles(String realpath, List files) {
	
	File realFile = new File(realpath);
	if (realFile.isDirectory()) {
		File[] subfiles = realFile.listFiles();
		for(File file :subfiles ){
			if(file.isDirectory()){
				getFiles(file.getAbsolutePath(),files);
			}else{
				if(!getFileType(file.getName()).equals("")) {
					files.add(file);
				}
			}
		}
	}
	return files;
}

public String getRealPath(HttpServletRequest request,String path){
	ServletContext application = request.getSession().getServletContext();
	String str = application.getRealPath("/");
	return new File(str).getPath();
}

public String getFileType(String fileName){
	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	Iterator<String> type = Arrays.asList(fileType).iterator();
	while(type.hasNext()){
		String t = type.next();
		if(fileName.toLowerCase().endsWith(t)){
			return t;
		}
	}
	return "";
}
%>