
package com.fengjx.wechat.sdk.fastweixin.api;

import com.fengjx.wechat.sdk.fastweixin.api.config.ApiConfig;
import com.fengjx.wechat.sdk.fastweixin.api.enums.ResultType;
import com.fengjx.wechat.sdk.fastweixin.api.response.*;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;
import com.fengjx.wechat.sdk.fastweixin.util.StrUtil;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class UserAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(UserAPI.class);

    public UserAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 获取关注者列表
     *
     * @param nextOpenid 下一个用户的ID
     * @return 关注者列表对象
     */
    public GetUsersResponse getUsers(String nextOpenid) {
        GetUsersResponse response = null;
        LOG.debug("获取关注者列表.....");
        String url = BASE_API_URL + "cgi-bin/user/get?access_token=#";
        if (StrUtil.isNotBlank(nextOpenid)) {
            url += "&next_openid=" + nextOpenid;
        }
        BaseResponse r = executeGet(url);
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JsonUtil.toBean(r.getErrmsg(), GetUsersResponse.class);
        }
        return response;
    }

    /**
     * 设置关注者备注
     *
     * @param openid 关注者ID
     * @param remark 备注内容
     * @return 调用结果
     */
    public ResultType setUserRemark(String openid, String remark) {
        Validate.notNull(openid, "openid is null");
        LOG.debug("设置关注者备注.....");
        String url = BASE_API_URL + "cgi-bin/user/info/updateremark?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("openid", openid);
        param.put("remark", remark);
        BaseResponse response = executePost(url, JsonUtil.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 创建分组
     *
     * @param name 分组名称
     * @return 返回对象，包含分组的ID和名称信息
     */
    public CreateGroupResponse createGroup(String name) {
        CreateGroupResponse response = null;
        Validate.notNull(name, "name is null");
        LOG.debug("创建分组.....");
        String url = BASE_API_URL + "cgi-bin/groups/create?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> group = new HashMap<String, Object>();
        group.put("name", name);
        param.put("group", group);
        BaseResponse r = executePost(url, JsonUtil.toJson(param));
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JsonUtil.toBean(r.getErrmsg(), CreateGroupResponse.class);
        }
        return response;
    }

    /**
     * 获取所有分组信息
     *
     * @return 所有分组信息列表对象
     */
    public GetGroupsResponse getGroups() {
        GetGroupsResponse response = null;
        LOG.debug("获取所有分组信息.....");
        String url = BASE_API_URL + "cgi-bin/groups/get?access_token=#";
        BaseResponse r = executeGet(url);
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JsonUtil.toBean(r.getErrmsg(), GetGroupsResponse.class);
        }
        return response;
    }

    /**
     * 通过关注者ID获取所在分组信息
     *
     * @param openid 关注者ID
     * @return 所在分组信息
     */
    public String getGroupIdByOpenid(String openid) {
        Validate.notNull(openid, "openid is null");
        LOG.debug("通过关注者ID获取所在分组信息.....");
        String result = null;
        String url = BASE_API_URL + "cgi-bin/groups/getid?access_token=#";
        Map<String, String> params = new HashMap<String, String>();
        params.put("openid", openid);
        BaseResponse r = executePost(url, JsonUtil.toJson(params));
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            result = JsonUtil.toMap(r.getErrmsg()).get("groupid").toString();
        }
        return result;
    }

    /**
     * 修改分组信息
     *
     * @param groupid 分组ID
     * @param name 新名称
     * @return 调用结果
     */
    public ResultType updateGroup(Integer groupid, String name) {
        Validate.notNull(groupid, "groupid is null");
        Validate.notNull(name, "name is null");
        LOG.debug("修改分组信息.....");
        String url = BASE_API_URL + "cgi-bin/groups/update?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> group = new HashMap<String, Object>();
        group.put("id", groupid);
        group.put("name", name);
        param.put("group", group);
        BaseResponse response = executePost(url, JsonUtil.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 移动关注者所在分组
     *
     * @param openid 关注者ID
     * @param toGroupid 新分组ID
     * @return 调用结果
     */
    public ResultType moveGroupUser(String openid, String toGroupid) {
        Validate.notNull(openid, "openid is null");
        Validate.notNull(toGroupid, "toGroupid is null");
        LOG.debug("移动关注者所在分组.....");
        String url = BASE_API_URL + "cgi-bin/groups/members/update?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("openid", openid);
        param.put("to_groupid", toGroupid);

        BaseResponse response = executePost(url, JsonUtil.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 获取关注者信息
     *
     * @param openid 关注者ID
     * @return 关注者信息对象
     */
    public GetUserInfoResponse getUserInfo(String openid) {
        Validate.notNull(openid, "openid is null");
        GetUserInfoResponse response = null;
        LOG.debug("获取关注者信息.....");
        String url = BASE_API_URL + "cgi-bin/user/info?access_token=#&lang=zh_CN&openid=" + openid;
        BaseResponse r = executeGet(url);
        if (null == r.getErrcode() || "".equals(r.getErrcode())) {
            response = JsonUtil.toBean(r.getErrmsg(), GetUserInfoResponse.class);
        }
        return response;
    }
}
