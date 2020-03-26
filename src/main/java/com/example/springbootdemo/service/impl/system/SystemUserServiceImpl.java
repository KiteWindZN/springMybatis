package com.example.springbootdemo.service.impl.system;

import com.example.springbootdemo.dao.entity.system.SystemUser;
import com.example.springbootdemo.dao.mapper.system.SystemUserMapper;
import com.example.springbootdemo.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Override
    public List<SystemUser> getAllSystemUser(){
        return systemUserMapper.getAllSystemUser();
    }
    @Override
    public SystemUser getSystemUserByLoginName(String loginName){
        return systemUserMapper.getSystemUserByLoginName(loginName);
    }
}
