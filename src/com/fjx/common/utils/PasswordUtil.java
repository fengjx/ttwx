package com.fjx.common.utils;

import com.encryptcore.EnCryptCore;


public class PasswordUtil {

	private static String  key="fengjx-wechat-!@#";
	
	
	public static String encode(String conent){
		EnCryptCore core=new EnCryptCore();
		String password=core.encrypt(key, conent);
		return password;
		
	}
	
	
	public static String decode(String conent){
		EnCryptCore core=new EnCryptCore();
		String mingwen=core.decrypt(key,conent);
		return mingwen;
		
		
	}
	
	
	
	public static void main(String args[]){
		String content="BC29CD7FD3DA94D37840C1D88AD2FE35";
		System.out.println(decode(content));
	}
	

	
	
}
