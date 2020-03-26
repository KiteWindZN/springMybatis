package com.example.springbootdemo.service.system;

import com.example.springbootdemo.dao.entity.system.SystemUser;

import java.util.List;

public interface SystemUserService {
    public List<SystemUser> getAllSystemUser();
    public SystemUser getSystemUserByLoginName(String loginName);
}
