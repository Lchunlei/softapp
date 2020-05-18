package com.chun.lei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Created by lcl on 2020/5/15 0015
 */
@SpringBootApplication
@MapperScan("com.chun.lei.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
        System.out.println("--------------------系统管理已启动-----------------------");
    }
}
