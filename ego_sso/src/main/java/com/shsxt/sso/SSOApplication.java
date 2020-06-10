package com.shsxt.sso;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SSO启动类
 */
@SpringBootApplication
//开启dubbo
@EnableDubboConfiguration
//扫描包
@MapperScan("com.shsxt.sso.mapper")
public class SSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class);
    }
}
