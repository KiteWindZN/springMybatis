package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.entity.UserInfo;
import com.example.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/getAll")
    public List<UserInfo> queryAllUsers(){
        return userInfoService.getAll();
    }

    @GetMapping("/getUserByID")
    public UserInfo getUserByID(@RequestParam String id){
        return userInfoService.getUserByID(Long.parseLong(id));
    }

    @PostMapping("/insertUser")
    public void inserUser(@RequestBody UserInfo userInfo){
        userInfoService.insertUser(userInfo);
    }

    @PostMapping("/updateUser")
    public void updaterUser(@RequestBody UserInfo userInfo){
        userInfoService.updateUser(userInfo);
    }

    @GetMapping("/deleteUser")
    public void deleteUser(@RequestParam String id){
        userInfoService.deleteUser(Long.parseLong(id));
    }
}
