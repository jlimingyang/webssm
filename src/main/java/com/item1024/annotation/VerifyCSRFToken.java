package com.item1024.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/8/4
 *  跨站请求仿照注解
 */
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyCSRFToken {
    /**
     * 需要验证防跨站请求
     */
    public abstract boolean verify() default true;
}
