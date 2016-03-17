
package com.fengjx.modules.sys.listener;

import com.fengjx.commons.plugin.db.Record;
import org.springframework.context.ApplicationEvent;

/**
 * 用户注册事件
 *
 *
 * @Created by fengjianxin
 * @date 2015/9/19
 */
public class RegisterEvent extends ApplicationEvent {

    public RegisterEvent(Record record) {
        super(record);
    }

}
