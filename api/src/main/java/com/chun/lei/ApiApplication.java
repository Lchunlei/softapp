package com.chun.lei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Created by lcl on 2020/4/24 0024
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.chun.lei.mapper")
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);
        System.out.println("--------------------接口文档地址-----------------------");
        System.out.println("http://localhost:8901/mv/swagger-ui.html");
        System.out.println("--------------------数据库监控台-----------------------");
        System.out.println("http://localhost:8901/mv/druid/login.html");
        System.out.println("---->账号：admin");
        System.out.println("---->密码：adminlcl");

    }

}
