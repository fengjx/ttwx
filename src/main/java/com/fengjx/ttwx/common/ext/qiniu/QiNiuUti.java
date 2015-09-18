package com.fengjx.ttwx.common.ext.qiniu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fengjx.ttwx.common.utils.GetPropertiesVal;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

public class QiNiuUti {
	  private static final Logger logger = LoggerFactory.getLogger(QiNiuUti.class);
	private static String uptoken = null;
	// 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
	private final static UploadManager uploadManager = new UploadManager();

	private static BucketManager bucketManager;

	private static String bucket;
	private static Auth auth;

	static {
		try {
			String ak = GetPropertiesVal.getLabel("qiniu.ak");
			String sk = GetPropertiesVal.getLabel("qiniu.sk");
			bucket = GetPropertiesVal.getLabel("qiniu.bucket");
			if (StringUtils.isNotBlank(ak) && StringUtils.isNotBlank(sk)) {
				  auth = Auth.create(ak, sk);
				uptoken = auth.uploadToken(bucket);
				bucketManager = new BucketManager(auth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param key
	 * @param file
	 * @throws Exception 
	 */
	public static void uploadFile(byte[] byteOrFile, String key) throws Exception {
		try {
			if (key.startsWith("/"))
				key = key.substring(1, key.length());
			String token=getToken(key, false);
			Response res = uploadManager.put(byteOrFile, key, token);
			if (res.isOK()) {
				//System.out.println(res);
				logger.debug("upload success");
			} else {
				throw new Exception("status:"+res.statusCode+",error:"+res.error);
			}
		} catch (QiniuException e) {
			logger.error(e.getMessage(), e);
			Response r = e.response;
			// 请求失败时简单状态信息
			logger.error(r.error);
			
			try {
				// 响应的文本信息
				logger.error(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
			throw e;
		}
	}
	
	public static void uploadFile(File byteOrFile, String key,boolean overwrite) throws Exception {
		try {
			if (key.startsWith("/"))
				key = key.substring(1, key.length());
			 String token=getToken(key, overwrite);
			Response res = uploadManager.put(byteOrFile, key, token);
			if (res.isOK()) {
				//System.out.println(res);
				logger.debug("upload success");
			} else {
				throw new Exception("status:"+res.statusCode+",error:"+res.error);
			}
		} catch (QiniuException e) {
			logger.error(e.getMessage(), e);
			Response r = e.response;
			// 请求失败时简单状态信息
			logger.error(r.error);
			
			try {
				// 响应的文本信息
				logger.error(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
			throw e;
		}
	}

	private static String getToken(String key, boolean overwrite) {
		return overwrite?auth.uploadToken(bucket, key):uptoken;
	}


	public static boolean isStoreInQiNiu() {
		return StringUtils.isNotBlank(uptoken);
	}

	public static List<String> listFile(String prefix, int limit,
			String delimiter) {
		BucketManager.FileListIterator it = bucketManager
				.createFileListIterator(bucket, prefix, limit, delimiter);
		List<String> list = new ArrayList<String>();
		while (it.hasNext()) {
			FileInfo[] items = it.next();
			if (items.length > 0) {
				for (int i = 0; i < items.length; i++) {
					FileInfo fi = items[i];
					if (fi != null)
						list.add(fi.key);
				}
			}

		}
		return list;
	}
}
