
package com.fengjx.ttwx.modules.common.controler;

import com.fengjx.ttwx.modules.common.model.DataDict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典
 *
 * @author fengjx.
 * @date：2015/5/20 0020
 */
@Controller
@RequestMapping("/dict")
public class DictController extends MyController {

    @Autowired
    private DataDict dataDict;

    @RequestMapping("selecter")
    public ModelAndView selecter(String groupCode) {
        ModelAndView mv = new ModelAndView("common/inc/dict");
        mv.addObject("dicts", dataDict.getGroupDict(groupCode));
        return mv;
    }

}
