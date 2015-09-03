
package com.fengjx.ttwx.modules.sys.model;

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
    private Dict dict;

    @Test
    public void testJsTemplate() {
        System.out.println(dict.jsTemplate());
    }

}
