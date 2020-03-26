package com.example.springbootdemo.dao.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityCode;
    private String districtCode;
    private String streetCode;
    private String communityName;
    private String extCode;
    private String status;
    private List<Village> villageList;
}
