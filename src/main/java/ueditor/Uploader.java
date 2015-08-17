package ueditor;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.ext.qiniu.QiNiuUti;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * UEditor文件上传辅助类
 */
public class Uploader {

	private static Logger logger = Logger.getLogger(Uploader.class);

	// 文件大小常量, 单位kb
	private static final int MAX_SIZE = 500 * 1024;
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";

	private HttpServletRequest request = null;
	private String title = "";

	// 保存路径
	private String savePath = "upload";
	// 保存路径（非站点路径）
	private String mySavePath = "upload";

	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf",
			".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	// 文件大小限制，单位Byte
	private long maxSize = 0;

	private HashMap<String, String> errorInfo = new HashMap<String, String>();
	private Map<String, String> params = null;
	// 上传的文件数据
	private InputStream inputStream = null;

	public static final String ENCODEING = System.getProperties().getProperty(
			"file.encoding");

	public Uploader(HttpServletRequest request) {
		this.request = request;
		this.params = new HashMap<String, String>();

		this.setMaxSize(Uploader.MAX_SIZE);

		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); // 默认成功
		// 未包含文件上传域
		tmp.put("NOFILE",
				"\\u672a\\u5305\\u542b\\u6587\\u4ef6\\u4e0a\\u4f20\\u57df");
		// 不允许的文件格式
		tmp.put("TYPE",
				"\\u4e0d\\u5141\\u8bb8\\u7684\\u6587\\u4ef6\\u683c\\u5f0f");
		// 文件大小超出限制
		tmp.put("SIZE",
				"\\u6587\\u4ef6\\u5927\\u5c0f\\u8d85\\u51fa\\u9650\\u5236");
		// 请求类型错误
		tmp.put("ENTYPE", "\\u8bf7\\u6c42\\u7c7b\\u578b\\u9519\\u8bef");
		// 上传请求异常
		tmp.put("REQUEST", "\\u4e0a\\u4f20\\u8bf7\\u6c42\\u5f02\\u5e38");
		// 未找到上传文件
		tmp.put("FILE", "\\u672a\\u627e\\u5230\\u4e0a\\u4f20\\u6587\\u4ef6");
		// IO异常
		tmp.put("IO", "IO\\u5f02\\u5e38");
		// 目录创建失败
		tmp.put("DIR", "\\u76ee\\u5f55\\u521b\\u5efa\\u5931\\u8d25");
		// 未知错误
		tmp.put("UNKNOWN", "\\u672a\\u77e5\\u9519\\u8bef");

		this.parseParams();

	}

	public void upload() throws Exception {
		boolean isMultipart = ServletFileUpload
				.isMultipartContent(this.request);
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}

		if (this.inputStream == null) {
			this.state = this.errorInfo.get("FILE");
			return;
		}

		// 存储title
		this.title = this.getParameter("pictitle");
		try {
			String savePath = this.getFolder(this.savePath);
			if (!this.checkFileType(this.originalName)) {
				this.state = this.errorInfo.get("TYPE");
				return;
			}

			this.fileName = this.getName(this.originalName);
			this.type = this.getFileExt(this.fileName);
			this.url = savePath + "/" + this.fileName;

			FileOutputStream fos = new FileOutputStream(
					this.getPhysicalPath(this.url));
			BufferedInputStream bis = new BufferedInputStream(this.inputStream);
			byte[] buff = new byte[128];
			int count = -1;

			while ((count = bis.read(buff)) != -1) {
				fos.write(buff, 0, count);
			}

			bis.close();
			fos.close();

			this.state = this.errorInfo.get("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			this.state = this.errorInfo.get("IO");
		}
	}

	/**
	 * @param isPath
	 *            savePath是否是物理路径
	 * @throws Exception
	 * @author fengjx
	 * @date 2014年3月10日
	 */
	public void myUpload(boolean isPath) throws Exception {

		if (QiNiuUti.isStoreInQiNiu()) {
			uploadToQiNiu();
		} else {

			boolean isMultipart = ServletFileUpload
					.isMultipartContent(this.request);
			if (!isMultipart) {
				this.state = this.errorInfo.get("NOFILE");
				return;
			}

			if (this.inputStream == null) {
				this.state = this.errorInfo.get("FILE");
				return;
			}

			// 存储title
			this.title = this.getParameter("pictitle");

			try {

				if (!this.checkFileType(this.originalName)) {
					this.state = this.errorInfo.get("TYPE");
					return;
				}

				FileOutputStream fos = null;
				this.fileName = this.getName(this.originalName);
				this.type = this.getFileExt(this.fileName);
				if (isPath) {// 如果是物理路径
					this.url = this.getMyFolder(this.savePath) + "/"
							+ this.fileName;
					String upPath = this.mySavePath + url;
					fos = new FileOutputStream(upPath);
				} else {
					String savePath = this.getFolderByRoot(this.savePath);
					this.url = savePath + "/" + this.fileName;
					fos = new FileOutputStream(
							this.getPhysicalPathByRoot(this.url));
				}
				BufferedInputStream bis = new BufferedInputStream(
						this.inputStream);
				byte[] buff = new byte[128];
				int count = -1;

				while ((count = bis.read(buff)) != -1) {
					fos.write(buff, 0, count);
				}

				bis.close();
				fos.close();

				this.state = this.errorInfo.get("SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				this.state = this.errorInfo.get("IO");
			}
		}

	}

	/**
	 * 上传到七牛服务器
	 */
	public void uploadToQiNiu() {
		boolean isMultipart = ServletFileUpload
				.isMultipartContent(this.request);
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}

		if (this.inputStream == null) {
			this.state = this.errorInfo.get("FILE");
			return;
		}

		// 存储title
		this.title = this.getParameter("pictitle");

		try {

			if (!this.checkFileType(this.originalName)) {
				this.state = this.errorInfo.get("TYPE");
				return;
			}

			
			 
			this.fileName = this.originalName;//this.getName(this.originalName);
			this.type = this.getFileExt(this.fileName);
		 
			String savePath = this.getFolderByRoot(this.savePath);
			this.url =  savePath + "/" + this.fileName;
			 
			

			BufferedInputStream bis = new BufferedInputStream(this.inputStream);
			byte[] buff = new byte[128];
			int count = -1;

			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

			while ((count = bis.read(buff)) != -1) {
				swapStream.write(buff, 0, count);
			}
			byte[] fileByte = swapStream.toByteArray();  

			QiNiuUti.uploadFile(fileByte, this.getUrl());
			this.state = this.errorInfo.get("SUCCESS");
		} catch (Exception e) {
			logger.error(e);
			this.state = this.errorInfo.get("IO");
		}

	}

	/**
	 * 接受并保存以base64格式上传的文件
	 * 
	 * @param fieldName
	 */
	public void uploadBase64(String fieldName) {
		String savePath = this.getFolder(this.savePath);
		String base64Data = this.request.getParameter(fieldName);
		this.fileName = this.getName("test.png");
		this.url = savePath + "/" + this.fileName;
		try {
			File outFile = new File(this.getPhysicalPath(this.url));
			OutputStream ro = new FileOutputStream(outFile);
			byte[] b = Base64.decodeBase64(base64Data);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			ro.write(b);
			ro.flush();
			ro.close();
			this.state = this.errorInfo.get("SUCCESS");
		} catch (Exception e) {
			this.state = this.errorInfo.get("IO");
		}
	}

	public String getParameter(String name) {

		return this.params.get(name);

	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private void parseParams() {

		DiskFileItemFactory dff = new DiskFileItemFactory();
		try {
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setSizeMax(this.maxSize);
			sfu.setHeaderEncoding(Uploader.ENCODEING);

			FileItemIterator fii = sfu.getItemIterator(this.request);

			while (fii.hasNext()) {
				FileItemStream item = fii.next();
				// 普通参数存储
				if (item.isFormField()) {

					this.params.put(item.getFieldName(),
							this.getParameterValue(item.openStream()));

				} else {

					// 只保留一个
					if (this.inputStream == null) {
						this.inputStream = item.openStream();
						this.originalName = item.getName();
						return;
					}

				}

			}

		} catch (Exception e) {
			this.state = this.errorInfo.get("UNKNOWN");
		}

	}

	/**
	 * 依据原始文件名生成新文件名
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis() + this.getFileExt(fileName);
	}

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * 
	 * @param path
	 * @return
	 */
	private String getFolder(String path) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		path += "/" + formater.format(new Date());
		File dir = new File(this.getPhysicalPath(path));
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.state = this.errorInfo.get("DIR");
				return "";
			}
		}
		return path;
	}

	/**
	 * @param path
	 * @return
	 * @author fengjx
	 * @date 2014年3月10日
	 */
	private String getFolderByRoot(String path) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		path += "/" + formater.format(new Date());
		File dir = new File(this.getPhysicalPathByRoot(path));
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.state = this.errorInfo.get("DIR");
				return "";
			}
		}
		return path;
	}

	/**
	 * 自定义非站点物理路径上传
	 * 
	 * @param url
	 * @return
	 * @author fengjx
	 * @date 2014年9月16日
	 */
	private String getMyFolder(String url) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		url += "/" + formater.format(new Date());
		String path = this.mySavePath + url;
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.state = this.errorInfo.get("DIR");
				return "";
			}
		}
		return url;
	}

	/**
	 * 根据传入的虚拟路径获取物理路径
	 * 
	 * @param path
	 * @return
	 */
	private String getPhysicalPath(String path) {
		String servletPath = this.request.getServletPath();
		String realPath = this.request.getSession().getServletContext()
				.getRealPath(servletPath);
		return new File(realPath).getParent() + "/" + path;
	}

	/**
	 * 根据传入的虚拟路径获取物理路径（相对应根路径）
	 * 
	 * @param path
	 * @return
	 * @author fengjx
	 * @date 2014年3月10日
	 */
	private String getPhysicalPathByRoot(String path) {
		String realPath = this.request.getSession().getServletContext()
				.getRealPath("/");
		return new File(realPath).getPath() + "/" + path;
	}

	/**
	 * 从输入流中获取字符串数据
	 * 
	 * @param in
	 *            给定的输入流， 结果字符串将从该流中读取
	 * @return 从流中读取到的字符串
	 */
	private String getParameterValue(InputStream in) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String result = "";
		String tmpString = null;

		try {

			while ((tmpString = reader.readLine()) != null) {
				result += tmpString;
			}

		} catch (Exception e) {
			// do nothing
		}

		return result;

	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(long size) {
		this.maxSize = size * 1024;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}

	public String getMySavePath() {
		return mySavePath;
	}

	public void setMySavePath(String mySavePath) {
		this.mySavePath = mySavePath;
	}

}
