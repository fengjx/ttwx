package com.fjx.common.utils;

import java.io.File;



/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @version FileUtil.java 2014年9月16日
 */
public class FileUtil {
	
	
	/**
	 * 创建文件目录
	 */
	public static void mkdir(File file){
		if (!file.exists()) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建文件目录
	 */
	public static void mkdir(String path){
		File file = new File(path);
		mkdir(file);
	}
}
