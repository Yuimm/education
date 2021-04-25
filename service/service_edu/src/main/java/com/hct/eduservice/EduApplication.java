package com.hct.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 启动类
 * @author: HCT
 * @create: 2021/04/25 13:07
 * @VVVersion 1.0
 **/
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.hct"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
