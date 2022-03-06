package com.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.mapper.EnvironmentRangeMapper;
import com.community.dao.pojo.EnvironmentRange;
import com.community.service.EnvironmentRangeService;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentRangeServiceImpl extends ServiceImpl<EnvironmentRangeMapper, EnvironmentRange> implements EnvironmentRangeService {
}
