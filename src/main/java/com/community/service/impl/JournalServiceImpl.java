package com.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.mapper.JournalMapper;
import com.community.dao.pojo.Journal;
import com.community.service.JournalService;
import org.springframework.stereotype.Service;

@Service
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements JournalService {
}
