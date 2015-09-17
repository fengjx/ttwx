
package com.fengjx.ttwx.modules.sys.controller.display;

import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
