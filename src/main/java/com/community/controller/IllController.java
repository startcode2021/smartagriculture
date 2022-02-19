package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Place;
import com.community.dao.pojo.ill;
import com.community.service.IllService;
import com.community.service.PlaceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class IllController {

    @Autowired
    IllService illService;

    @Autowired
    PlaceService placeService;

    @GetMapping("/ill/{page}/{limit}/{place}")
    public String intelligent(@PathVariable int page, @PathVariable int limit, @PathVariable String place, Model model){
        if (page < 1){
            page = 1;
        }
        Page<ill> pageParam = new Page<>(page, limit);
        if(place.equals("0")){
            illService.page(pageParam, new QueryWrapper<ill>().orderByDesc("id"));
        }else {
            illService.page(pageParam, new QueryWrapper<ill>().orderByDesc("id").eq("region", place));
        }
        List<ill> illList = pageParam.getRecords();
        List<Place> placeList = placeService.list();
        model.addAttribute("PlaceList", placeList);
        model.addAttribute("illList",illList);
        model.addAttribute("pageParam",pageParam);
        model.addAttribute("CurrentPlace",place);
        return "ill/ill";
    }
    @ApiOperation(value = "新增虫害")
    @PostMapping("/Addill")
    public String AddRobotType(String ill_name, String robot_name,String region,String robot_num,String operation){
        ill temp = new ill();
        temp.setIll_name(ill_name);
        temp.setRobot_name(robot_name);
        temp.setRegion(region);
        temp.setRobot_num(Integer.parseInt(robot_num));
        temp.setOperation(operation);
        temp.setAdd_date(DateTime.now().toString());
        boolean flag = illService.save(temp);
        if (flag){
            return "redirect:/ill/1/5/0";
        }else
        {
            return "redirect:/ill/1/5/0";
        }
    }
    @ApiOperation(value = "删除虫害传感器")
    @PostMapping("/Deleteill/{place}")
    public void DeleteRobotType(String id, @PathVariable String place, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        List<ill> illList;
        if(place.equals("0")) {
            illList = illService.list(new QueryWrapper<ill>().orderByDesc("id"));
        }else{
            illList = illService.list(new QueryWrapper<ill>().orderByDesc("id").eq("region",place));
        }
        if(illList.size()<Integer.parseInt(id))
        {
            PrintWriter writer = response.getWriter();
            String msg = null;
            msg = "alert('删除失败');history.go(-1)";
            writer.print("<script type='text/javascript'>" + msg + "</script>");
            writer.flush();
            writer.close();
        }else {
            ill obj = illList.get(Integer.parseInt(id) - 1);
            boolean flag = illService.remove(new QueryWrapper<ill>().eq("id", obj.getId()));
            if (flag) {
                PrintWriter writer = response.getWriter();
                String msg = null;
                msg = "alert('删除成功');location.href='/ill/1/5/"+place+"'";
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
