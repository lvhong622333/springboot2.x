package com.lvhong.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lvhong.web.mapper.TmSysUserMapper;
import com.lvhong.web.pojo.TmSysUser;
import com.lvhong.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private TmSysUserMapper userMapper;

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TmSysUser queryUserInfoByUserName(String userName) {
		TmSysUser queryUserInfoByUserName = userMapper.queryUserInfoByUserName(userName);
		return queryUserInfoByUserName;
	}
}
