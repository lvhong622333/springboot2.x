package com.lvhong.web.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class AuthCredential extends  SimpleCredentialsMatcher{
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//通过token 获取用户名和密码
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String encryptPassword =  MD5Utils.encrypt(userToken.getUsername(), String.valueOf(userToken.getPassword()), 3);
		userToken.setPassword(encryptPassword.toCharArray());
		return super.doCredentialsMatch(userToken, info);
	}
}
