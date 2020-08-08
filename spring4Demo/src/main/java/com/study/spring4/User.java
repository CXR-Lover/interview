package com.study.spring4;

/**
 * @Classname User
 * @Description TODO
 * @Date 2020/1/30 13:33
 * @Created by CXR
 */
public class User {


    private String name;

    public void setName2(String name) {
        this.name = name;
    }

    User(String str) {
        System.out.println("初始化对象");
    }

    public void hello() {
        System.out.println("userName = [" + name + "]");
    }
}

