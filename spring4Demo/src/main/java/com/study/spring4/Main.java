package com.study.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2020/1/30 13:34
 * @Created by CXR
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) applicationContext.getBean("helloWorld");
        user.hello();
    }
}
