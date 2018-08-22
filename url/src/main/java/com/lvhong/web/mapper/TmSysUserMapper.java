package com.lvhong.web.mapper;

import java.util.List;
import com.lvhong.web.pojo.TmSysUser;

public interface TmSysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TmSysUser record);

    int insertSelective(TmSysUser record);

    TmSysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmSysUser record);

    int updateByPrimaryKey(TmSysUser record);

	List<TmSysUser> queryUserInfo();

	TmSysUser queryUserInfoByUserName(String userName);
}