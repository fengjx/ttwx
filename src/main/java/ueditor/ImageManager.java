package ueditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fengjx.ttwx.common.ext.qiniu.QiNiuUti;

@Component
public class ImageManager {

 @Autowired
 @Qualifier("localFsStorage")
 private IImagesStorage localFsStorage;
 @Autowired
 @Qualifier("qiNiuStorage")
 private IImagesStorage qiNiuStorage;
 
 
 
 public String getImagesStr(){
	if( QiNiuUti.isStoreInQiNiu()){
		return qiNiuStorage.getImages();
	}else{
		return localFsStorage.getImages();
	}
	 
	 
 }
       
}
