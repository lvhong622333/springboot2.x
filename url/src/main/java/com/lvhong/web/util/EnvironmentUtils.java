package com.lvhong.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.mysql.jdbc.StringUtils;

@Component
public class EnvironmentUtils {

	@Resource
	private Environment env;
	
	public  String getMessage(String key) throws UnsupportedEncodingException {
		Locale locale = Locale.getDefault();
		String[] activeProfiles = env.getActiveProfiles();
		for (String proName : activeProfiles) {
			if(proName.indexOf(locale.getLanguage()) != -1) {				
				ResourceBundle bundle = ResourceBundle.getBundle(proName, locale);
				//String value = new String(bundle.getString(key).getBytes("ISO8859-1"),"UTF-8");
				String value = bundle.getString(key);
				if(!StringUtils.isNullOrEmpty(value)) {
					return value;
				}
			}
		}
		return "";
	}

}