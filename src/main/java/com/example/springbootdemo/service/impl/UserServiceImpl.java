package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.dao.entity.UserInfo;
import com.example.springbootdemo.dao.mapper.UserInfoMapper;
import com.example.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userMapper;
    @Override
    public List<UserInfo> getAll(){
        return userMapper.getAll();
    }
    @Override
    public UserInfo getUserByID(Long id){
        return userMapper.getUserById(id);
    }
    @Override
    public void insertUser(UserInfo userInfo){
        userMapper.insertUser(userInfo);
    }
    @Override
    public void updateUser(UserInfo userInfo){
        userMapper.updateUser(userInfo);
    }
    @Override
    public void deleteUser(Long id){
        userMapper.deleteUserById(id);
    }
}
