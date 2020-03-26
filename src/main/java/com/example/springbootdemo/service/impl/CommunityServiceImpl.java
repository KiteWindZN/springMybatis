package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.dao.entity.Community;
import com.example.springbootdemo.dao.mapper.CommunityMapper;
import com.example.springbootdemo.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl  implements CommunityService {
    @Autowired
    CommunityMapper communityMapper;
    @Override
    public List<Community> selCommunityVillage(){
        return communityMapper.selCommunityVillage();
    }
    @Override
    public Community selCommunityVillageById(Long id){
        return communityMapper.selCommunityVillageById(id);
    }

    @Override
    public List<Community> getAllCommunity(){
        return communityMapper.getAllCommunity();
    }
}
