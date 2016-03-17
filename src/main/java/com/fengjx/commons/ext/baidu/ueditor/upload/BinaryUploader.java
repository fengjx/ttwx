
package com.fengjx.commons.ext.baidu.ueditor.upload;

import com.fengjx.commons.ext.baidu.ueditor.define.AppInfo;
import com.fengjx.commons.ext.baidu.ueditor.PathFormat;
import com.fengjx.commons.ext.baidu.ueditor.define.BaseState;
import com.fengjx.commons.ext.baidu.ueditor.define.FileType;
import com.fengjx.commons.ext.baidu.ueditor.define.State;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

    private static final Logger LOG = LoggerFactory.getLogger(BinaryUploader.class);

    public static final State save(HttpServletRequest request,
            Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }
        try {
            FileItemIterator iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                fileStream = iterator.next();
                if (!fileStream.isFormField()) {
                    break;
                }
                fileStream = null;
            }

            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = fileStream.getName();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }
            savePath = PathFormat.parse(savePath, originFileName);
            String physicalPath;
            if ("local".equals(conf.get("storageType"))) {
                physicalPath = conf.get("rootPath") + savePath;
            } else {
                physicalPath = savePath;
            }
            InputStream is = fileStream.openStream();
            // 从配置文件读取文件存储方式
            IStorageManager storageManager = StorageFactory.createStorageManager((String) conf
                    .get("storageType"));
            State storageState = storageManager.saveFileByInputStream(is,
                    physicalPath, maxSize);
            is.close();
            if (storageState.isSuccess()) {
                storageState.putInfo("title", fileStream.getName());
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }
            return storageState;
        } catch (FileUploadException e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
