package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.mapper.PlaceMapper;
import com.community.dao.pojo.Place;
import com.community.service.PlaceService;
import com.community.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    RedisUtil redis;

    /*   redis预先加载数据表到内存中
    @PostConstruct
    public void init() {
        List<Place> placeList = placeMapper.selectList(new QueryWrapper<Place>().orderByDesc("id"));
        for (Place place : placeList) {
            redis.set(Integer.toString(place.getId()),place);
        }
    }*/
}
