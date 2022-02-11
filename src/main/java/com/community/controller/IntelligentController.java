package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Place;
import com.community.dao.pojo.RobotType;
import com.community.service.PlaceService;
import com.community.service.RobotTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class IntelligentController {

    @Autowired
    RobotTypeService robotTypeService;

    @Autowired
    PlaceService placeService;

    @GetMapping("/intelligent/{page}/{limit}/{place}")
    public String intelligent(@PathVariable int page, @PathVariable int limit, @PathVariable String place,Model model){
        if (page < 1){
            page = 1;
        }
        Page<RobotType> pageParam = new Page<>(page, limit);
        if(place.equals("0")){
            robotTypeService.page(pageParam, new QueryWrapper<RobotType>().orderByDesc("id"));
        }else {
            robotTypeService.page(pageParam, new QueryWrapper<RobotType>().orderByDesc("id").eq("robot_address", place));
        }
        List<RobotType> RobotTypeList = pageParam.getRecords();
        List<Place> placeList = placeService.list();
        model.addAttribute("PlaceList", placeList);
        model.addAttribute("RobotTypeList",RobotTypeList);
        model.addAttribute("pageParam",pageParam);
        model.addAttribute("CurrentPlace",place);
        return "intelligentcontrol/intelligentcontrol";
    }

    @PostMapping("/AddRobotType")
    public String AddRobotType(String index_num, String robot_name,String robot_type,String robot_address,String robot_num,String other){
        RobotType robotType = new RobotType();
        robotType.setIndex_num(Integer.parseInt(index_num));
        robotType.setRobot_name(robot_name);
        robotType.setRobot_type(robot_type);
        robotType.setRobot_address(robot_address);
        robotType.setRobot_num(Integer.parseInt(robot_num));
        robotType.setOther(other);
        Date date = new Date();
        robotType.setAdd_date(date);
        boolean flag = robotTypeService.save(robotType);
        if (flag){
            return "redirect:/intelligent/1/5/0";
        }else
        {
            return "redirect:/intelligent/1/5/0";
        }
    }
    @PostMapping("/DeleteRobotType/{place}")
    public void DeleteRobotType(String id, @PathVariable String place, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        List<RobotType> RobotTypeList;
        if(place.equals("0")) {
            RobotTypeList= robotTypeService.list(new QueryWrapper<RobotType>().orderByDesc("id"));
        }else{
            RobotTypeList = robotTypeService.list(new QueryWrapper<RobotType>().orderByDesc("id").eq("robot_address",place));
        }
        if(RobotTypeList.size()<Integer.parseInt(id))
        {
            PrintWriter writer = response.getWriter();
            String msg = null;
            msg = "alert('删除失败');history.go(-1)";
            writer.print("<script type='text/javascript'>" + msg + "</script>");
            writer.flush();
            writer.close();
        }else {
            RobotType obj = RobotTypeList.get(Integer.parseInt(id) - 1);
            boolean flag = robotTypeService.remove(new QueryWrapper<RobotType>().eq("id", obj.getId()));
            if (flag) {
                PrintWriter writer = response.getWriter();
                String msg = null;
                msg = "alert('删除成功');location.href='/intelligent/1/5/"+place+"'";
                writer.print("<script type='text/javascript'>" + msg + "</script>");
                writer.flush();
                writer.close();
            } else {
                PrintWriter writer = response.getWriter();
                String msg = null;
                msg = "alert('删除失败');history.go(-1)";
                writer.print("<script type='text/javascript'>" + msg + "</script>");
                writer.flush();
                writer.close();
            }
        }
    }

}
