
package com.fengjx.mapper;

import com.fengjx.common.utils.CommonUtils;
import com.fengjx.wechat.base.dao.DataDictMapper;
import com.fengjx.wechat.base.model.DataDict;
import com.fengjx.wechat.base.service.DataDictServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class DictTest {

    @Autowired
    private DataDictMapper dataDictMapper;

    @Autowired
    private DataDictServiceImpl dataDictServiceImpl;

    @Test
    public void find() {
        try {
            DataDict dict = dataDictMapper.selectByPrimaryKey("22");
            System.out.println(dict.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void insert() {
        try {
            DataDict dataDict = new DataDict();
            dataDict.setId(CommonUtils.getPrimaryKey());
            dataDictServiceImpl.testRoolBack(dataDict);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
