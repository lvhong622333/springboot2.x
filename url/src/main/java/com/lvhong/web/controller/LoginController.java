package com.lvhong.web.controller;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.druid.util.StringUtils;
import com.lvhong.web.pojo.TmSysUser;
import com.lvhong.web.service.UserService;
import com.lvhong.web.util.EnvironmentUtils;

@Controller
public class LoginController {
	
	@Resource
	private EnvironmentUtils environmentUtils;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String loginUrl() {
		return "/pages/login";
	}
	
	@RequestMapping("/urlInfo/login")
	public String login(String userName ,String password,Model model,HttpSession session) throws UnsupportedEncodingException {
		if(StringUtils.isEmpty(userName)) {
			model.addAttribute("userNameError", environmentUtils.getMessage("userNameError"));
            return "/pages/login";
		}
		if(StringUtils.isEmpty(password)) {
			model.addAttribute("passwordError", environmentUtils.getMessage("passwordEmpty"));
            return "/pages/login";
		}
		Subject subject = SecurityUtils.getSubject(); // 获取Subject单例对象
		if(!subject.isAuthenticated()) {			
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			try {
				subject.login(token);		
			}catch(AuthenticationException e) {
				model.addAttribute("passwordError", environmentUtils.getMessage("passwordError"));
				return "/pages/login";
			}
		}
		TmSysUser user = userService.queryUserInfoByUserName(userName);
		subject.getSession().setAttribute("user", user);
		return "/pages/homePage";
	}
	
}
