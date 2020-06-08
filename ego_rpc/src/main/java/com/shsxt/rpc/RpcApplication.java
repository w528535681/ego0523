package com.shsxt.rpc;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper接口包
@MapperScan("com.shsxt.rpc.mapper")
//开启dubbo
@EnableDubboConfiguration
public class RpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class);
    }
}
