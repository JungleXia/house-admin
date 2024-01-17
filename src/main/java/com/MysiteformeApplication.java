package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages="com", exclude = {
        DataSourceAutoConfiguration.class})
@MapperScan("com.mysiteforme.admin.dao")
public class MysiteformeApplication extends SpringBootServletInitializer {

	/**
	 * 把web项目打包成war包部署到外部tomcat运行时，需要改变启动方式
	 * @param args
	 * 2019年10月22日下午4:40:16
	 */
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(MysiteformeApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(MysiteformeApplication.class, args);
	}
}
