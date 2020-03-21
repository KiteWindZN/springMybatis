package com.example.springbootdemo.dao.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String userLevel;
    private String userCode;

    public UserInfo(){}
    public UserInfo(String userName, String password, String userLevel, String userCode) {
        this.userName=userName;
        this.password=password;
        this.userLevel=userLevel;
        this.userCode=userCode;
    }

}
