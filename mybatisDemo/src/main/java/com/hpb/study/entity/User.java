package com.hpb.study.entity;

import lombok.Data;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2020/2/6 14:08
 * @Created by CXR
 */
@Data
public class User {

    private Long id;

    private String username;

    private String loginame;

    private String password;

    private String position;

    private String department;

    private String email;

    private String phonenum;

    private Byte ismanager;

    private Byte isystem;

    private Byte status;

    private String description;

    private String remark;

    private Long tenantId;

}