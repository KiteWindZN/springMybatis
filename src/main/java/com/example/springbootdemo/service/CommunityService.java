package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.entity.Community;

import java.util.List;

public interface CommunityService {
    public List<Community> selCommunityVillage();
    public Community selCommunityVillageById(Long id);
    public List<Community> getAllCommunity();
}
