package com.shsxt.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
//扫描mapper接口
@MapperScan("com.shsxt.manager.mapper")
public class ManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class,args);
	}
}