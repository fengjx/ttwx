package com.fjx.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
	/* Description: 向FTP服务器上传文件 
	 * @Version1.0 Jul 27, 2013 19:31:09 PM by donglg
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	*/  
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
            ftp.enterLocalPassiveMode();    //没有这个 ftp.storeFile(filename, ftpIn)返回false
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);     //没有这个上传文件大小会变化
            directory(ftp, path);
            ftp.storeFile(filename, input);
//			ftp.changeWorkingDirectory(url);
//			ftp.storeFile(filename, input); 
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}
	
	/**
	 * Description: 向FTP服务器批量上传文件 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param list
	 * @return
	 */
	public static boolean uploadFileList(String url,int port,String username, String password,List<Map<String,Object>> list) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			
			for(Map<String,Object> map:list){
				ftp.enterLocalPassiveMode();    //没有这个 ftp.storeFile(filename, ftpIn)返回false
	            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);     //没有这个上传文件大小会变化
	            directory(ftp, (String)map.get("path"));
	            ftp.storeFile((String)map.get("filename"),(InputStream)map.get("input"));
//				ftp.changeWorkingDirectory(url);
//				ftp.storeFile(filename, input); 
	            ((InputStream)map.get("input")).close();
			}
			
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}
	
	/**
	 * Description: 向FTP服务器删除文件 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param path
	 * @param filename
	 * @return
	 */
	public static boolean deleteFile(String url,int port,String username, String password, String filename) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			
			ftp.deleteFile(filename);
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}
	
	/**
	 * Description: 向FTP服务器删除目录
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param path
	 * @return
	 */
	public static boolean removeDirectory(String url,int port,String username, String password, String path) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			removeDirectory(ftp, path, true);
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}
	
	/**
	 *  make directory 上传目录
	 * @throws Exception 
	 */
	private static void directory(FTPClient ftp,String path) throws IOException{
		
        if (path != null && !"".equals(path.trim())) {
//            String[] pathes = path.split("/");
//            for (String onepath : pathes) {
//                if (onepath == null || "".equals(onepath.trim())) {
//                    continue;
//                }
//                onepath=new String(onepath.getBytes("GBK"),"iso-8859-1");                    
                if (!ftp.changeWorkingDirectory(path)) {
                	ftp.makeDirectory(path);
                	ftp.changeWorkingDirectory(path);
                }
//            }
        }
	}
	
	/**
	 * delete all subDirectory and files.  删除文件目录
	 * @param ftp
	 * @param path
	 * @param isAll
	 * @return
	 * @throws IOException
	 */
    private static boolean removeDirectory(FTPClient ftp,String path, boolean isAll)  
            throws IOException {  
          
        if (!isAll) {  
            return ftp.removeDirectory(path);  
        }  
  
        FTPFile[] ftpFileArr = ftp.listFiles(path);  
        if (ftpFileArr == null || ftpFileArr.length == 0) {  
            return ftp.removeDirectory(path);
        }  
        //   
        for (FTPFile ftpFile : ftpFileArr) {  
            String name = ftpFile.getName();  
            if (ftpFile.isDirectory()) {  
                removeDirectory(ftp,path + "/" + name, true);  
            } else if (ftpFile.isFile()) {  
                ftp.deleteFile(path + "/" + name);
            } else if (ftpFile.isSymbolicLink()) {  
  
            } else if (ftpFile.isUnknown()) {  
  
            }  
        }  
        return ftp.removeDirectory(path);  
    }  
    
	public static void main(String[] args) throws FileNotFoundException {
		
		//上传文件
		String file = "D:\\logo1.png";
		FileInputStream in=new FileInputStream(new File(file));  
		FtpUtil.uploadFile("127.0.0.1", 2121, "uploadimg", "123456", "/","logo1.png", in); 
		
		//删除文件
//		FtpUtil.deleteFile("127.0.0.1", 2121, "uploadimg", "123456", "logo1.png");

		//上传目录
//		String file = "D:\\logo1.png";
//		FileInputStream in=new FileInputStream(new File(file));  
//		FtpUtil.uploadFile("127.0.0.1", 2121, "uploadimg", "123456", "/ss/","logo1.png", in);
		
		//删除目录下所有的文件
//		FtpUtil.removeDirectory("127.0.0.1", 2121, "uploadimg", "123456", "/ss/"); 
		
		

	}
	
	
	
}
