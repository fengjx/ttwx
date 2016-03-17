package com.fengjx.commons.utils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class AesUtilTest {
	
	private String msg;
	private String encryptMsg;
	
	@Before
	public void setUp(){
		msg="abc";
		encryptMsg="08b17a4d15f9a54d6fde4abc7269f866";
		
	}

	@Test
	public void test() {
		String msg="abc";
		String encrypt=AesUtil.encrypt(msg);
		System.out.println(encrypt);
		String decrypt=AesUtil.decrypt(encrypt);
		Assert.assertEquals(msg, decrypt);
	}
	
	@Test
	public void testDecrypt(){
		String decrypt=AesUtil.decrypt(encryptMsg);
		Assert.assertEquals(msg, decrypt);
	}

}
