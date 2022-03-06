package com.community;

import com.community.dao.pojo.Place;
import com.community.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SmartAgricultureApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redis;
    @Test
    void contextLoads() {

    }
    @Test
    public void redistest(){
        System.out.println(redis.get("1"));
    }
}
