package com.example.springbootdemo.dao.mapper.system;

import com.example.springbootdemo.dao.entity.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemUserMapper {
    public List<SystemUser> getAllSystemUser();
    public SystemUser getSystemUserByLoginName(String loginName);
}
