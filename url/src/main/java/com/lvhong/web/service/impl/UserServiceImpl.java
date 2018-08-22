package com.lvhong.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.lvhong.web.mapper.TmSysUserMapper;
import com.lvhong.web.mapper.UserEsMapper;
import com.lvhong.web.pojo.TmSysUser;
import com.lvhong.web.service.UserService;
import com.lvhong.web.util.RedisCache;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private TmSysUserMapper userMapper;
	
	@Resource
	private UserEsMapper userEsMapper;
	
	@Override
	@RedisCache(type = TmSysUser.class)
	public TmSysUser queryUserInfoByUserName(String userName) {
		TmSysUser queryUserInfoByUserName = userMapper.queryUserInfoByUserName(userName);
		userEsMapper.save(queryUserInfoByUserName);
		return queryUserInfoByUserName;
	}
}
