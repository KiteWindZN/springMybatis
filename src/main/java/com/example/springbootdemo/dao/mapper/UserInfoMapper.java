package com.example.springbootdemo.dao.mapper;

import com.example.springbootdemo.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    List<UserInfo> getAll();
    UserInfo getUserById(Long id);
    void insertUser(UserInfo userInfo);
    void updateUser(UserInfo userInfo);
    void deleteUserById(Long id);
}
