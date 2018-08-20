package com.lvhong;

import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用于处理静态资源拦截
 * @author lvhong
 *
 */
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 配置模板资源路径
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
	}

	@Bean
	public BaseInterceptor getSecurityInterceptor() {
		return new BaseInterceptor();
	}

}
