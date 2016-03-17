
package com.fengjx.commons.plugin.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动绑定request参数标记注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.PARAMETER
})
public @interface BindBean {
}
