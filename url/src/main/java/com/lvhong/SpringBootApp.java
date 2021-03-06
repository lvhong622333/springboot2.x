package com.lvhong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * @author lvhong
 * @since jdk1.8
 * @version 1.0 springboot2.0.4工程启动类
 * @MapperScan 用于集成mybatis接口，生命周期交由spring管理
 */
@SpringBootApplication
public class SpringBootApp extends SpringBootServletInitializer{

	/**
	 * 用于以war包的形式部署项目配置
	 * @param args
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootApp.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
	
}
