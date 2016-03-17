
package com.fengjx.commons.ext.baidu.ueditor.hunter;

import com.fengjx.commons.ext.baidu.ueditor.define.BaseState;
import com.fengjx.commons.ext.baidu.ueditor.define.MultiState;
import com.fengjx.commons.ext.baidu.ueditor.define.State;
import com.fengjx.commons.ext.qiniu.QiNiuUti;

import java.io.File;
import java.util.List;
import java.util.Map;

public class QiNiuFileManager implements IFileManager {

    private static final String PREFIX = "upload/images/";

    private String dir = null;
    private String rootPath = null;
    private String[] allowFiles = null;
    private int count = 0;

    public QiNiuFileManager(Map<String, Object> conf) {
        this.rootPath = (String) conf.get("rootPath");
        this.dir = this.rootPath + conf.get("dir");
        this.allowFiles = this.getAllowFiles(conf.get("allowFiles"));
        this.count = (Integer) conf.get("count");
    }

    @Override
    public State listFile(int index) {
        List<String> fileList = QiNiuUti.listFile(PREFIX, 1000, null);
        State state = null;
        if (index < 0 || index > fileList.size()) {
            state = new MultiState(true);
        } else {
            state = this.getState(fileList);
        }
        state.putInfo("start", index);
        state.putInfo("total", fileList.size());
        return state;
    }

    public String getImages() {
        String prefix = "upload/images/";
        List<String> listKey = QiNiuUti.listFile(prefix, 1000, null);

        StringBuffer sb = new StringBuffer();
        for (String key : listKey) {
            sb.append("/");
            sb.append(key);
            sb.append("ue_separate_ue");
        }
        String imgStr = sb.toString();
        if (imgStr != "" && imgStr.length() > 0) {
            imgStr = imgStr
                    .substring(0, imgStr.lastIndexOf("ue_separate_ue"))
                    .replace(File.separator, "/").trim();
        }

        return imgStr;
    }

    private State getState(List<String> fileList) {
        MultiState state = new MultiState(true);
        BaseState fileState;
        for (String url : fileList) {
            fileState = new BaseState(true);
            fileState.putInfo("url", buildUrl(url));
            state.addState(fileState);
        }
        return state;
    }

    private String buildUrl(String url) {
        if (url.startsWith("/")) {
            return url;
        }
        return "/" + url;
    }

    private String[] getAllowFiles(Object fileExt) {
        String[] exts = null;
        String ext = null;
        if (fileExt == null) {
            return new String[0];
        }
        exts = (String[]) fileExt;
        for (int i = 0, len = exts.length; i < len; i++) {
            ext = exts[i];
            exts[i] = ext.replace(".", "");
        }
        return exts;
    }

}
