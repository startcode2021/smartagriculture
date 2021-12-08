package com.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.mapper.PlaceMapper;
import com.community.dao.pojo.Place;
import com.community.service.PlaceService;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {
}
