
package com.fengjx.ttwx.common.plugin.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Mapper {
    public String table() default "";
    public String id() default "id";
    public String pid() default "";
}
