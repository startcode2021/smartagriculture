package com.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Place;
import com.community.dao.pojo.RobotRecord;
import com.community.dao.pojo.RobotType;
import com.community.service.PlaceService;
import com.community.service.RobotRecordService;
import com.community.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class IndexController {

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    PlaceService placeService;

    @Autowired
    RobotRecordService robotRecordService;

    @RequestMapping("/index/{place}")
    public String index(@PathVariable String place,Model model){
        List<Place> placeList = placeService.list();
        model.addAttribute("PlaceList",placeList);
        if(place.equals("0"))
        {
            model.addAttribute("selectPlace",placeList.get(0));
        }else{
            Place place1 = placeService.getOne(new QueryWrapper<Place>().eq("place_name",place));
            model.addAttribute("selectPlace",place1);
        }
        return "index";
    }
    @RequestMapping("/index/table/{page}/{limit}/{place}")
    public String indexTable(@PathVariable int page, @PathVariable int limit, @PathVariable String place,Model model){
        if (page < 1){
            page = 1;
        }
        Page<RobotRecord> pageParam = new Page<>(page, limit);
        if(place.equals("0")){
            robotRecordService.page(pageParam, new QueryWrapper<RobotRecord>().orderByDesc("id"));
        }else {
            robotRecordService.page(pageParam, new QueryWrapper<RobotRecord>().orderByDesc("id").eq("region", place));
        }
        List<RobotRecord> RobotRecordList = pageParam.getRecords();
        List<Place> placeList = placeService.list();
        model.addAttribute("PlaceList",placeList);
        model.addAttribute("RobotRecordList",RobotRecordList);
        model.addAttribute("pageParam",pageParam);
        model.addAttribute("CurrentPlace",place);
        return "indexTable";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/login";
    }
    @PostMapping("/kaiguang")
    @ResponseBody
    public Msg kaiguang(String place, String type){
        UpdateWrapper uw = new UpdateWrapper<>();
        uw.eq("place_name",place);
        Place place1 = placeService.getOne(new QueryWrapper<Place>().eq("place_name",place));
        if(type.equals("1"))
        {
            if(place1.getLighting()==1)
                uw.set("lighting",0);
            else
                uw.set("lighting",1);

        }else if(type.equals("2")){
            if(place1.getFan()==1)
                uw.set("fan",0);
            else
                uw.set("fan",1);

        }else if(type.equals("3")){
            if(place1.getWater_valve()==1)
                uw.set("water_valve",0);
            else
                uw.set("water_valve",1);
        }
        Boolean flag = placeService.update(uw);
        if(flag)
        {
            return Msg.success();
        }else{
            return Msg.error();
        }


    }


}