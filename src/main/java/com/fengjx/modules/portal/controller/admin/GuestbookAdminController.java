
package com.fengjx.modules.portal.controller.admin;

import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.portal.model.GuestBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 联系我们
 *
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller
@RequestMapping("${adminPath}/portal/guestbook")
public class GuestbookAdminController extends MyController {

    @Autowired
    private GuestBook guestBook;

    @RequestMapping(value = "")
    public String view() {
        return "portal/admin/guestbook";
    }

    @RequestMapping(value = "pageList", method = RequestMethod.POST)
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return guestBook.paginate(getNotBlankRequestMap(request), "order by in_time desc").convert();
    }

}
