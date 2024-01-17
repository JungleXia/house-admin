package com.mysiteforme.admin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.druid.initialSize}")
	private String initialSize;

	@Value("${spring.datasource.druid.maxActive}")
	private String maxActive;

	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource primaryDataSource() {
		DataSource druidDataSource = DataSourceBuilder.create().build();
//		druidDataSource.setMaxActive(Integer.valueOf(maxActive));
//		druidDataSource.setInitialSize(Integer.valueOf(initialSize));
		return druidDataSource;
	}

	@Bean(name = "secondDataSource")
	@Qualifier("secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.app-datasource")
	public DataSource secondaryDataSource() {
		DataSource druidDataSource = DataSourceBuilder.create().build();
		// druidDataSource.setMaxActive(Integer.valueOf(maxActive));
		// druidDataSource.setInitialSize(Integer.valueOf(initialSize));
		return druidDataSource;
	}

//	@Bean(name = "thirdDataSource")
//	@Qualifier("thirdDataSource")
//	@ConfigurationProperties(prefix = "spring.lwxweb2-datasource")
//	public DataSource thirdDataSource() {
//		DataSource druidDataSource = DataSourceBuilder.create().build();
//		// druidDataSource.setMaxActive(Integer.valueOf(maxActive));
//		// druidDataSource.setInitialSize(Integer.valueOf(initialSize));
//		return druidDataSource;
//	}

	@Bean
	@Primary
	public JdbcTemplate primaryJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "secondJdbcTemplate")
	public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

//	@Bean(name = "thirdJdbcTemplate")
//	public JdbcTemplate thirdJdbcTemplate(@Qualifier("thirdDataSource") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}

}