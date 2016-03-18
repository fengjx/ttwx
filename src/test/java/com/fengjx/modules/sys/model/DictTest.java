
package com.fengjx.modules.sys.model;

import com.fengjx.modules.sys.service.SysDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Created by FengJianxin on 2015/9/3.
 * @Email xd-fjx@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class DictTest {

    @Autowired
    private SysDictService dictService;

    @Test
    public void testJsTemplate() {
        System.out.println(dictService.jsTemplate());
    }

}
