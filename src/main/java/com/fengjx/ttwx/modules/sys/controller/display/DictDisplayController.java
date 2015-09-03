
package com.fengjx.ttwx.modules.sys.controller.display;

import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典
 *
 * @author fengjx.
 * @date：2015/5/20 0020
 */
@Controller
@RequestMapping("/dict")
public class DictDisplayController extends MyController {

    @Autowired
    private Dict dict;

    /**
     * 查询字典列表
     *
     * @param groupCode
     * @return
     */
    @RequestMapping("selecter")
    public ModelAndView selecter(String groupCode) {
        ModelAndView mv = new ModelAndView("common/inc/dict");
        mv.addObject("dicts", dict.findDictList(groupCode));
        return mv;
    }

    @RequestMapping("label")
    @ResponseBody
    public String label(String value, String group) {
        return dict.getDictLabel(value, group);
    }

    @ResponseBody
    @RequestMapping(value = "js", produces = "text/javascript;charset=UTF-8")
    public String data() {
        return dict.jsTemplate();
    }

}
