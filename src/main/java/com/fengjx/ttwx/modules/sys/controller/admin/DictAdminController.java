
package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 字典管理
 *
 * @Created by FengJianxin on 2015/8/23.
 * @Email xd-fjx@qq.com
 */
@Controller
@RequestMapping("${adminPath}/sys/dict")
public class DictAdminController extends MyController {

    @Autowired
    private Dict dict;

    @RequestMapping(value = "")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView("sys/dict");
        return mv;
    }


    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        Map<String, Object> attrs = getRequestMap(request);
        return dict.paginate(attrs).convert();
    }

}
