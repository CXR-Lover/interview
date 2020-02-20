package com.hpb.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname MybatisDemoApplication
 * @Description TODO
 * @Date 2020/2/6 11:29
 * @Created by CXR
 */

@SpringBootApplication
@ComponentScan("com.hpb.study")
public class MybatisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class);
    }
}