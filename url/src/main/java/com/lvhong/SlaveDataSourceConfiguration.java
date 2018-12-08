package com.lvhong;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

public class SlaveDataSourceConfiguration {
	@Configuration
	@MapperScan(basePackages = "com.lvhong.web.slavemapper", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
	public class slaveDataSourceConfiguration {
		/**
		 * 生成数据源.@Primary 注解声明为默认数据源
		 */
		@Bean(name = "slaveDataSource")
		@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
		@Primary
		public DataSource testDataSource() {
			return new DruidDataSource();
		}

		/**
		 * 创建 SqlSessionFactory
		 */
		@Bean(name = "slaveSqlSessionFactory")
		@Primary
		public SqlSessionFactory testSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource)
				throws Exception {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			bean.setConfigLocation(
					new PathMatchingResourcePatternResolver().getResource("classpath:mapper/config/mybatis-config.xml"));
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
			return bean.getObject();
		}

		/**
		 * 配置事务管理
		 */
		@Bean(name = "slaveTransactionManager")
		@Primary
		public DataSourceTransactionManager testTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Bean(name = "slaveSqlSessionTemplate")
		@Primary
		public SqlSessionTemplate testSqlSessionTemplate(
				@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}
}
