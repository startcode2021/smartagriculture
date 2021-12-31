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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Controller

public class IndexController {

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    PlaceService placeService;

    @Autowired
    RobotRecordService robotRecordService;

    /*
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
    }*/
    @RequestMapping("/index/{place}/{index}")
    public String index(@PathVariable String place,@PathVariable String index,Model model){
        List<Place> placeList = placeService.list();
        model.addAttribute("PlaceList",placeList);
        if(place.equals("0"))
        {
            model.addAttribute("selectPlace",placeList.get(0));
        }else{
            Place place1 = placeService.getOne(new QueryWrapper<Place>().eq("place_name",place));
            model.addAttribute("selectPlace",place1);
        }
        Place choose = (Place) model.getAttribute("selectPlace");
        //取20条传感器记录
        Page<RobotRecord> pageParam = new Page<>(1, 20);

        if(index.equals("0"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("air_temp",0).orderByDesc("add_time"));
        else if(index.equals("1"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("air_hump",0).orderByDesc("add_time"));
        else if(index.equals("2"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("ground_temp",0).orderByDesc("add_time"));
        else if(index.equals("3"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("ground_hump",0).orderByDesc("add_time"));
        else if(index.equals("4"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("lux",0).orderByDesc("add_time"));
        else if(index.equals("5"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("co2",0).orderByDesc("add_time"));
        else if(index.equals("6"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("nh3",0).orderByDesc("add_time"));
        else if(index.equals("7"))
            robotRecordService.page(pageParam ,new QueryWrapper<RobotRecord>().eq("region",choose.getPlace_name()).notIn("h2s",0).orderByDesc("add_time"));
        List<RobotRecord> RobotRecordList = pageParam.getRecords();
        List<String> time = new ArrayList<String>();
        List<Double> records = new ArrayList<Double>();
        for(int i = RobotRecordList.size()-1;i >= 0;i--)
        {
            time.add(RobotRecordList.get(i).getAdd_time());
            if(index.equals("0"))
                records.add(RobotRecordList.get(i).getAir_temp());
            else if(index.equals("1"))
                records.add(RobotRecordList.get(i).getAir_hump());
            else if(index.equals("2"))
                records.add(RobotRecordList.get(i).getGround_temp());
            else if(index.equals("3"))
                records.add(RobotRecordList.get(i).getGround_hump());
            else if(index.equals("4"))
                records.add(RobotRecordList.get(i).getLux());
            else if(index.equals("5"))
                records.add(RobotRecordList.get(i).getCo2());
            else if(index.equals("6"))
                records.add(RobotRecordList.get(i).getNh3());
            else if(index.equals("7"))
                records.add(RobotRecordList.get(i).getH2s());
        }
        model.addAttribute("chart",index);
        model.addAttribute("time_x",time);
        model.addAttribute("record_y",records);
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
    @RequestMapping("/Demo")
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // TODO Auto-generated method stub

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值并打印
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username="+username+" ,password="+password);

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write("进入后台了");
        out.flush();
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