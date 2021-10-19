package com.city.hcy.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

/**
 * 使用此注解标识DAO层需要日记记录的方法
 *
 */

public @interface RecordLog {
    /**
     * 日志记录描述
     *
     * @return
     */
    String desc() default "";
}