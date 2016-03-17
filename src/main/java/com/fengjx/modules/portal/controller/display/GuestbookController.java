
package com.fengjx.modules.portal.controller.display;

import com.fengjx.commons.plugin.db.annotation.BindBean;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.portal.bean.PortalGuestbook;
import com.fengjx.modules.portal.model.PortalGuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 联系我们
 *
 * @author fengjx. @date：2015/5/17 0017
 */
@Controller
@RequestMapping("/portal/guestbook")
public class GuestbookController extends MyController {

    @Autowired
    private PortalGuestbookService guestBook;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@BindBean PortalGuestbook guestbook) {
        validateRequired("name", "姓名不能为空");
        validateRequired("email", "邮箱不能为空");
        validateRequired("msg", "发送信息不能为空");
        guestbook.setInTime(new Date());
        guestBook.save(guestbook);
        return retSuccess();
    }

}
