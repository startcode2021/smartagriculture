package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.community.dao.pojo.EnvironmentRange;
import com.community.dao.pojo.Place;
import com.community.service.EnvironmentRangeService;
import com.community.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnvironmentRangeController {
    @Autowired
    EnvironmentRangeService environmentRangeService;
    @Autowired
    PlaceService placeService;

    @PostMapping("/addRange")
    public String addrange(String factor,String address, String upper_limit,String lower_limit) {
        Place place = placeService.getOne(new QueryWrapper<Place>().eq("place_name",address));
        EnvironmentRange environmentRange = new EnvironmentRange();
        environmentRange.setPlace_id(place.getId());
        environmentRange.setEnvironment(factor);
        environmentRange.setUpper_limit(Double.parseDouble(upper_limit));
        environmentRange.setLower_limit(Double.parseDouble(lower_limit));
        QueryWrapper<EnvironmentRange> queryWrapper = new QueryWrapper<EnvironmentRange>();
        queryWrapper.eq("place_id",environmentRange.getPlace_id());
        queryWrapper.eq("environment",environmentRange.getEnvironment());
        int count = environmentRangeService.count(queryWrapper);
        if(count != 0){
            UpdateWrapper<EnvironmentRange> updateWrapper = new UpdateWrapper<EnvironmentRange>();
            updateWrapper.eq("place_id",environmentRange.getPlace_id());
            updateWrapper.eq("environment",environmentRange.getEnvironment());
            environmentRangeService.update(environmentRange, updateWrapper);
        }else {
            boolean flag = environmentRangeService.save(environmentRange);
        }
        return  "redirect:/index/0/0";
    }
}
