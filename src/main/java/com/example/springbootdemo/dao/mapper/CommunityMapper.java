package com.example.springbootdemo.dao.mapper;

import com.example.springbootdemo.dao.entity.Community;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    public List<Community> selCommunityVillage();
    public List<Community> getAllCommunity();
    public Community selCommunityVillageById(Long id);
}
