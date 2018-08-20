package com.lvhong.web.service;

import com.lvhong.web.pojo.TmSysUser;

public interface UserService {

	TmSysUser queryUserInfoByUserName(String username);
	
}
