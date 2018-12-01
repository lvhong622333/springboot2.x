package com.lvhong;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lvhong.web.util.AuthCredential;
import com.lvhong.web.util.AuthRealm;

@Configuration
public class ShiroConfiguration {
	
	@Bean("credentialsMatcher")
	public AuthCredential authCredential() {
		AuthCredential AuthCredential = new AuthCredential();
		return AuthCredential;
	}
	
	@Bean("authRealm")
	public AuthRealm authRealm(@Qualifier("credentialsMatcher") AuthCredential authCredential) {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(authCredential);
		return authRealm;
	}
	
	@Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        creator.setProxyTargetClass(true);
        return creator;
    }
	
	@Bean
    public SessionsSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }
	
	@Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SessionsSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        //登出
        map.put("/login","anon");
        map.put("/urlInfo/login", "anon");
        map.put("/static/**", "anon");
        map.put("/urlInfo/register", "anon");
        map.put("/**","authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

}
