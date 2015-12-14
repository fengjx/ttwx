
package com.fengjx.modules.sys.listener;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * 用户注册事件
 *
 *
 * @Created by fengjianxin
 * @date 2015/9/19
 */
public class RegisterEvent extends ApplicationEvent {

    public RegisterEvent(Map<String, Object> attrs) {
        super(attrs);
    }

}
