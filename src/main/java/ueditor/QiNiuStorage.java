package ueditor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ext.qiniu.QiNiuUti;
import com.fengjx.ttwx.modules.common.constants.AppConfig;

/**
 * 七牛图片列表
 * @author dennykong
 *
 */
@Component("qiNiuStorage")
public class QiNiuStorage implements IImagesStorage  {
	
	@Override
	public   String getImages() {
		String prefix="upload/images/";
		List<String> listKey=QiNiuUti.listFile(prefix,100,null);
		 
		StringBuffer sb = new StringBuffer();
		for (String key : listKey) {
			sb.append("/");
			sb.append(key);
			sb.append("ue_separate_ue");
		}
		String imgStr = sb.toString();
		if (imgStr != ""&&imgStr.length()>0) {
			imgStr = imgStr
					.substring(0, imgStr.lastIndexOf("ue_separate_ue"))
					.replace(File.separator, "/").trim();
		}
		 
		return imgStr;
	}

	 

}
