
package com.fengjx.modules.wechat.model;

import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.web.page.PageContext;
import com.fengjx.modules.wechat.service.WechatReqMsgLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/10 0010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class ReqMsgLogTest {

    @Autowired
    private WechatReqMsgLogService wechatReqMsgLogService;

    @Test
    public void testInsert() {
    }

    @Test
    public void testPage() {
        Page<Map<String, Object>> page = wechatReqMsgLogService.paginate(1, 10, "select * from wechat_req_msg_log order by in_time");
        System.out.println(page);
        PageContext.setPageNumber(1);
        PageContext.setPageSize(10);
        Map<String,Object> attrs = new HashMap();
        attrs.put("public_account_id","8ac398a4494d3b6301494d41cf0d0000");
        page = wechatReqMsgLogService.paginate(attrs);
        System.out.println(page);
    }

}
