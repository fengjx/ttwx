
package com.fengjx.wechat.base.service;

import com.fengjx.wechat.base.dao.DataDictMapper;
import com.fengjx.wechat.base.model.DataDict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataDictServiceImpl {

    @Autowired
    private DataDictMapper dataDictMapper;

    public void testRoolBack(DataDict dataDict) {
        dataDictMapper.insertSelective(dataDict);
        if (true) {
            throw new RuntimeException();
        }
    }
}
