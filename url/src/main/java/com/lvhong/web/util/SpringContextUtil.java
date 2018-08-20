package com.lvhong.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 在无法使用spring容器注入的方式是实现对象获取时，手动获取容器中的对象
 * @author lvhong
 * @version 1.0
 * @since jdk1.8
 */

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 根据对象的名称或者id获取对象
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 根据类型获取对象
	 * @param c
	 * @return
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public static <T> T getBeanByClass(Class c) {
		return (T) applicationContext.getBean(c);
	}
	
}
