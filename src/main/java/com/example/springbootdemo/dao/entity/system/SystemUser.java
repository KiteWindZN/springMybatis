package com.example.springbootdemo.dao.entity.system;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SystemUser {
    private String id;
    private String loginName;
    private String userName;
    private String password;
    private String authorityCode;
    private String authorityLevel;

}
