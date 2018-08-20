package com.lvhong.web.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface RedisCache {
	
	/**
	 * 数据返回类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Class type();
	
	/**
	 * redis缓存有效时间
	 * @return
	 */
	int cacheTime() default 600;
	
}
