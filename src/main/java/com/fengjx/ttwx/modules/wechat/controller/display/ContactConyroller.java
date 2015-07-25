
package com.fengjx.ttwx.modules.wechat.controller.display;

import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.wechat.model.GuestBook;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 联系我们
 *
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller
@RequestMapping("/contact")
public class ContactConyroller extends MyController {

    @Autowired
    private GuestBook guestBook;

    @RequestMapping(value = "/guestbook/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> save(HttpServletRequest request) {
        Map<String, String> resMap = new HashMap<String, String>();
        if (StringUtils.isBlank(request.getParameter("name"))) {
            resMap.put("code", "0");
            resMap.put("msg", "姓名不能为空");
            return resMap;
        }
        if (StringUtils.isBlank(request.getParameter("email"))) {
            resMap.put("code", "0");
            resMap.put("msg", "邮箱不能为空");
            return resMap;
        }
        if (StringUtils.isBlank(request.getParameter("msg"))) {
            resMap.put("code", "0");
            resMap.put("msg", "发送信息不能为空");
            return resMap;
        }
        Map<String, Object> attrs = getRequestMap(request);
        attrs.put("in_time", new Date());
        guestBook.insert(attrs);
        return retSuccess();
    }

}
