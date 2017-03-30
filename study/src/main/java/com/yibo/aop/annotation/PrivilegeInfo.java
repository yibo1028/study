package com.yibo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 * @author Minhellic
 *
 */
@Target(ElementType.METHOD)//这个注解是应用在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeInfo {
	
    String value() default "";

}
