
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.utils.CommonUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/10 0010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class UserGroupTest {

    @Autowired
    private UserGroup userGroup;

    @Test
    public void testInsert() {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("id", CommonUtils.getPrimaryKey());
        attrs.put("name", "ssss");
        attrs.put("in_time", new Date());
        userGroup.insert(attrs);
    }

}
