package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    public List<UserInfo> getAll();
    public UserInfo getUserByID(Long id);
    public void insertUser(UserInfo userInfo);
    public void updateUser(UserInfo userInfo);
    public void deleteUser(Long id);
}
