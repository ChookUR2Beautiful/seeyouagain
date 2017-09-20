package com.xmniao.xmn.core.util.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestToken {
	/**
	 * 生成令牌
	 * @return
	 */
	boolean createToken() default false;
	/**
	 * 移除令牌
	 * @return
	*/
	boolean removeToken() default false;
	/**
	 * 令牌名称  多个令牌以,分割
	 * @return
    */
	String tokenName() default "";
}
