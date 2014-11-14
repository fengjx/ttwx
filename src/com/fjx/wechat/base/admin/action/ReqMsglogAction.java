package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.ReqMsgLogEntoty;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.base.admin.service.ReqMsgLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Todo 微信消息请记录
 * Created by fengjx on 14-11-1.
 * author email: xd-fjx@qq.com
 */
@RequestMapping("/admin/msglog")
@Controller
public class ReqMsglogAction extends BaseController {

	@Autowired
    private ReqMsgLogService reqMsgLogService;

    @RequestMapping(value="")
    public String view(HttpServletRequest request, String openid){
    	request.setAttribute("openid", openid);
        return "/wechat/admin/msg_log/msg_log";
    }

    @RequestMapping(value="/pageList")
	@ResponseBody
    public Pagination<ReqMsgLogEntoty> pageList(HttpServletRequest request, ReqMsgLogEntoty reqMsgLog){
        SysUserEntity sysUser = getLoginSysUser(request);
        return reqMsgLogService.pageList(reqMsgLog, sysUser.getWechatPublicAccount());
    }


}
