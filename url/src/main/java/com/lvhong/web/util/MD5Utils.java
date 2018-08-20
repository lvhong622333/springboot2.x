package com.lvhong.web.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {
	/**
	 * 用于加密
	 * @param username 用户名
	 * @param password 密码
	 * @param frequency 经过几次混合加密（推荐使用值：3）
	 * @return String
	 */
	public static String encrypt(String username , String password , Integer frequency) {
		 return new Md5Hash(password, username, frequency).toHex();
	}
}
