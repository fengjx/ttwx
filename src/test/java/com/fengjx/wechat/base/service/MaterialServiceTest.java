
package com.fengjx.wechat.base.service;

import com.fengjx.common.mybatis.Page;
import com.fengjx.common.mybatis.PageContext;
import com.fengjx.common.mybatis.PageWapper;
import com.fengjx.common.utils.JsonUtil;
import com.fengjx.wechat.base.model.SysUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @Test
    public void testPage() {
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(10);
        PageContext.setPage(page);
        SysUser user = new SysUser();
        user.setId("4028c681492dd51601492dd6fdd90000");
        PageWapper news = this.materialService.getListPageByType("news", user);
        System.out.println(JsonUtil.toJson(news));
    }
}
