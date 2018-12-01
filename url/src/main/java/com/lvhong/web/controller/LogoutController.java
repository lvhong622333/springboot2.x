package com.lvhong.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping("/urlInfo/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {			
			subject.logout();
		}
		return "/pages/login";
	}
}
