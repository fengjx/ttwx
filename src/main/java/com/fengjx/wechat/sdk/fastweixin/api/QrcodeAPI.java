
package com.fengjx.wechat.sdk.fastweixin.api;

import com.fengjx.wechat.sdk.fastweixin.api.config.ApiConfig;
import com.fengjx.wechat.sdk.fastweixin.api.enums.QrcodeType;
import com.fengjx.wechat.sdk.fastweixin.api.response.BaseResponse;
import com.fengjx.wechat.sdk.fastweixin.api.response.QrcodeResponse;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维码相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class QrcodeAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(QrcodeAPI.class);

    public QrcodeAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 创建二维码
     *
     * @param actionName 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过1800
     * @return 二维码对象
     */
    public QrcodeResponse createQrcode(QrcodeType actionName, String sceneId, Integer expireSeconds) {
        Validate.notNull(actionName, "actionName is null");
        Validate.notNull(sceneId, "actionInfo is null");
        LOG.debug("创建二维码信息.....");
        QrcodeResponse response = null;
        String url = BASE_API_URL + "cgi-bin/qrcode/create?access_token=#";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("action_name", actionName);
        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_id", sceneId);
        actionInfo.put("scene", scene);
        param.put("action_info", actionInfo);
        if (null != expireSeconds && 0 != expireSeconds) {
            param.put("expire_seconds", expireSeconds);
        }
        BaseResponse r = executePost(url, JsonUtil.toJson(param));
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JsonUtil.toBean(r.getErrmsg(), QrcodeResponse.class);
        }
        return response;
    }
}
