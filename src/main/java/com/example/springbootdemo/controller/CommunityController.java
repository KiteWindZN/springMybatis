package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.entity.Community;
import com.example.springbootdemo.service.CommunityService;
import com.example.springbootdemo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Contended;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    CommunityService communityService;

    @GetMapping("/selCommunityVillage")
    public ResponseResult selCommunityVillage(){
        return ResponseResult.success(communityService.selCommunityVillage());
    }
    @GetMapping("/selCommunityVillageById")
    public Community selCommunityVillageById(@RequestParam String id){
        Long cId=Long.parseLong(id);
        return communityService.selCommunityVillageById(cId);
    }
    @GetMapping("/getAllCommunity")
    public List<Community> getAllCommunity(){
        return communityService.getAllCommunity();
    }
    @GetMapping("/index")
    public ModelAndView gotoIndex(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
