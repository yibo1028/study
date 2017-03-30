package com.yibo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindParam {
	String value();

	boolean required() default true;

	boolean smart() default false;
/*	
	BindWeiXinUserAction[] actions() default {};*/
}
