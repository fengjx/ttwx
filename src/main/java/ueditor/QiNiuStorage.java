package ueditor;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ext.qiniu.QiNiuUti;

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
		List<String> listKey=QiNiuUti.listFile(prefix,1000,null);
		 
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
