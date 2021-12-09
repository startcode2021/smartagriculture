package com.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.mapper.IllMapper;
import com.community.dao.pojo.ill;
import com.community.service.IllService;
import org.springframework.stereotype.Service;

@Service
public class IllServiceImpl extends ServiceImpl<IllMapper, ill> implements IllService {

}
