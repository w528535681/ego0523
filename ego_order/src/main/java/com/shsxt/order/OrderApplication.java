package com.shsxt.order;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 订单启动类
 */
@SpringBootApplication
//开启Dubbo
@EnableDubboConfiguration
//Mapper扫描
@MapperScan("com.shsxt.order.mapper")
public class OrderApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class,args);
	}
}
