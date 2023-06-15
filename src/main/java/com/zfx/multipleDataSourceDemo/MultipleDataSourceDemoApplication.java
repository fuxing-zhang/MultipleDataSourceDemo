package com.zfx.multipleDataSourceDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zfx.multipleDataSourceDemo.web.mapper")
public class MultipleDataSourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourceDemoApplication.class, args);
    }

}
