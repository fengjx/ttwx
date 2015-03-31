
package com.fengjx.wechat.sdk.fastweixin.api;

import com.fengjx.wechat.sdk.fastweixin.api.config.ApiConfig;
import com.fengjx.wechat.sdk.fastweixin.api.enums.ResultType;
import com.fengjx.wechat.sdk.fastweixin.api.response.BaseResponse;
import com.fengjx.wechat.sdk.fastweixin.api.response.GetTokenResponse;
import com.fengjx.wechat.sdk.fastweixin.util.HttpUtil;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

import org.apache.commons.lang3.Validate;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * API基类，提供一些通用方法 包含自动刷新token、通用get post请求等
 * 
 * @author peiyu
 * @since 1.2
 */
public abstract class BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(BaseAPI.class);

    protected static final String BASE_API_URL = "https://api.weixin.qq.com/";

    protected final ApiConfig config;

    // 用于刷新token时锁住config，防止多次刷新
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    protected BaseAPI(ApiConfig config) {
        this.config = config;
    }

    /**
     * 刷新token
     */
    protected void refreshToken() {
        LOG.debug(this.config.getAppid() + "开始刷新access_token......");
        writeLock.lock();
        try {
            if (config.refreshing.compareAndSet(false, true)) {
                String url = BASE_API_URL + "cgi-bin/token?grant_type=client_credential&appid="
                        + this.config.getAppid() + "&secret=" + this.config.getSecret();
                HttpUtil.get(url, null, new HttpUtil.ResponseCallback() {
                    @Override
                    public void onResponse(int resultCode, String resultJson) {
                        if (HttpStatus.SC_OK == resultCode) {
                            GetTokenResponse response = JsonUtil.toBean(resultJson,
                                    GetTokenResponse.class);
                            BaseAPI.this.config.setAccessToken(response.getAccessToken());
                            LOG.debug("刷新access_token成功.....");
                        } else {
                            LOG.warn("获取access_token失败....");
                            LOG.warn("信息:{}", resultJson);
                        }
                    }
                });
            }
        } finally {
            config.refreshing.set(false);
            writeLock.unlock();
        }
    }

    /**
     * 通用post请求
     * 
     * @param url 地址，其中token用#代替
     * @param json 参数，json格式
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json) {
        return executePost(url, json, null);
    }

    /**
     * 通用post请求
     * 
     * @param url 地址，其中token用#代替
     * @param json 参数，json格式
     * @param file 上传的文件
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json, File file) {
        BaseResponse response = null;
        Validate.notNull(url, "url is null");
        String postUrl;
        List<File> files = null;
        if (null != file) {
            files = new ArrayList<File>();
            files.add(file);
        }
        readLock.lock();
        try {
            // 需要传token
            postUrl = url.replace("#", config.getAccessToken());
            response = HttpUtil.post(postUrl, json, files);
        } finally {
            readLock.unlock();
        }

        if (null == response
                || ResultType.ACCESS_TOKEN_TIMEOUT.getCode().toString()
                        .equals(response.getErrcode())) {
            if (!config.refreshing.get()) {
                refreshToken();
            }
            readLock.lock();
            try {
                LOG.debug("接口调用重试....");
                TimeUnit.SECONDS.sleep(1);
                postUrl = url.replace("#", config.getAccessToken());
                response = HttpUtil.post(postUrl, json, files);
            } catch (InterruptedException e) {
                LOG.error("线程休眠异常", e);
            } catch (Exception e) {
                LOG.error("请求出现异常", e);
            } finally {
                readLock.unlock();
            }
        }

        return response;
    }

    /**
     * 通用post请求
     * 
     * @param url 地址，其中token用#代替
     * @return 请求结果
     */
    protected BaseResponse executeGet(String url) {
        BaseResponse response = null;
        Validate.notNull(url, "url is null");
        String getUrl;
        readLock.lock();
        try {
            // 需要传token
            getUrl = url.replace("#", config.getAccessToken());
            response = HttpUtil.get(getUrl);
        } finally {
            readLock.unlock();
        }

        if (null == response
                || ResultType.ACCESS_TOKEN_TIMEOUT.getCode().toString()
                        .equals(response.getErrcode())) {
            if (!config.refreshing.get()) {
                refreshToken();
            }
            readLock.lock();
            try {
                LOG.debug("接口调用重试....");
                TimeUnit.SECONDS.sleep(1);
                getUrl = url.replace("#", config.getAccessToken());
                response = HttpUtil.get(getUrl);
            } catch (InterruptedException e) {
                LOG.error("线程休眠异常", e);
            } catch (Exception e) {
                LOG.error("请求出现异常", e);
            } finally {
                readLock.unlock();
            }
        }
        return response;
    }
}
