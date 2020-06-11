package com.shsxt.manager;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
//扫描mapper接口
@MapperScan("com.shsxt.manager.mapper")
//开启dubbo
@EnableDubboConfiguration
public class ManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class,args);
	}
}