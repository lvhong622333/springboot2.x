package com.lvhong.web.util;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentUtils {

	@Resource
	private Environment env;
	
	public  String getMessage(String key)  {
		Locale locale = Locale.getDefault();
		String[] activeProfiles = env.getActiveProfiles();
		for (String proName : activeProfiles) {
			if(proName.indexOf(locale.getLanguage()) != -1) {				
				ResourceBundle bundle = ResourceBundle.getBundle(proName, locale);
				String value = bundle.getString(key);
				if(!StringUtils.isNotBlank(value)) {
					return value;
				}
			}
		}
		return "";
	}

}
