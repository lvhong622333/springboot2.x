package com.lvhong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 重写spring拦截器，在拦截前后做处理
 * @author lvhong
 *
 */
public class BaseInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info(request.getRequestURI());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info(request.getRequestURI());
	}
}
