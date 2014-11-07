package com.fjx.common.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.fjx.wechat.base.web.admin.entity.SysUserEntity;
import com.fjx.wechat.config.AppConfig;
import com.sina.sae.storage.SaeStorage;
import com.sina.sae.util.SaeUserInfo;

/**
 * 控制器基类
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	protected SysUserEntity getLoginSysUser(HttpServletRequest request) {
		return (SysUserEntity) getSession(request).getAttribute(AppConfig.LOGIN_FLAG);
	}

	/**
	 * 当用户提交数据时，使用此模板方法，比如保存，更新操作 成功统一返回code=1，失败返回code=0
	 * 
	 * @param callack
	 * @param exceptionInfo
	 * @return
	 */
	protected Map<String, String> doResult(MyExecuteCallback callack,
			String exceptionInfo) {
		Map<String, String> res = new HashMap<String, String>();
		try {
			callack.execute();
			res.put("code", "1");
			res.put("msg", "操作成功");
		} catch (Exception e) {
			res.put("code", "0");
			if (StringUtils.isBlank(exceptionInfo)) {
				res.put("msg", e.getMessage());
			} else {
				res.put("msg", exceptionInfo);
			}
			logger.error(exceptionInfo, e);
		}
		return res;
	}

	/**
	 * 校验验证码
	 * 
	 * @param request
	 * @param validCode
	 * @return
	 */
	protected Map<String, String> compareValidCode(HttpServletRequest request,
			String valid_code) {
		Map<String, String> res = new HashMap<String, String>();
		res.put("code", "1");
		res.put("msg", "验证码正确");
		String code =  request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)+"";
		if (StringUtils.isBlank(code)) {
			res.put("code", "0");
			res.put("msg", "页面超时，请重试！");
		} else if (!code.toUpperCase().equals(valid_code.toUpperCase())) {
			res.put("code", "0");
			res.put("msg", "验证码错误！");
		}
		return res;
	}

	/**
	 * 将文件上传SAE Storage
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public Map<String, String> saeStorageUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> resMap = new HashMap<String, String>();
		resMap.put("code", "0");
		resMap.put("msg", "上传失败");
		resMap.put("url", "");
		resMap.put("fileName", "");
		PrintWriter out = response.getWriter();
		// 使用SaeUserInfo拿到改请求可写的路径
		String realPath = SaeUserInfo.getSaeTmpPath() + "/";
		try {
			// 使用common-upload上传文件至这个路径中
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				resMap.put("msg", "上传失败，无法上传临时目录");
				return resMap;
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024 * 1024);
			List<FileItem> items = null;
			items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					File fullFile = new File(item.getName());
					File uploadFile = new File(realPath, fullFile.getName());
					item.write(uploadFile);
					// 上传完毕后 使用SaeStorage往storage里面写
					SaeStorage ss = new SaeStorage();
					// 使用upload方法上传到域为image下
					ss.upload("image", realPath + fullFile.getName(), fullFile.getName());
					logger.debug("upload file:" + realPath + fullFile.getName());
					
					resMap.put("code", "1");
					resMap.put("msg", "上传成功");
					resMap.put("url", realPath + fullFile.getName());
					resMap.put("fileName", fullFile.getName());
				}
			}
			out.print("upload end...");
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			resMap.put("msg", "上传失败，上传过程出现异常");
		} finally {
			out.flush();
			out.close();
		}
		return resMap;
	}
	
	/**
     * 写出数据
     *
     * @param res 输出的字符串
     * @throws Exception
     */
    protected void write(String res, HttpServletResponse response) throws Exception {
        Writer writer = null;
        try {
        	res = (null == res?"":res);
        	response.setCharacterEncoding("UTF-8");
    		response.setHeader("Content-type", "text/html;charset=UTF-8");  
            writer = response.getWriter();
            logger.debug("输出JSON字符串："+res);
            writer.write(res);
        } catch (IOException e) {
            logger.error("输出JSON字符串异常");
            throw new Exception("write json string error");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error("关闭输出流异常,无法关闭会导致内存溢出");
                }
            }
        }
    }
}
