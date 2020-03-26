package com.example.springbootdemo.controller.system;

import com.example.springbootdemo.dao.entity.system.SystemUser;
import com.example.springbootdemo.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/systemUser")
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;

    @GetMapping("/getAllSystemUser")
    public List<SystemUser> getAllSystemUser(){
        return systemUserService.getAllSystemUser();
    }

    @GetMapping("/getSystemUserByLoginName")
    public SystemUser getSystemUserLoginName(@RequestParam String loginName){
        return systemUserService.getSystemUserByLoginName(loginName);
    }
}
