package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Place;
import com.community.service.PlaceService;
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
public class RegionController {

    @Autowired
    PlaceService placeService;

    @GetMapping("/region/{page}/{limit}")
    public String region(@PathVariable int page, @PathVariable int limit, Model model) {
        if (page < 1){
            page = 1;
        }
        Page<Place> pageParam = new Page<>(page, limit);
        placeService.page(pageParam,new QueryWrapper<Place>().orderByDesc("id"));
        List<Place> placeList = pageParam.getRecords();
        model.addAttribute("PlaceList", placeList);
        model.addAttribute("pageParam",pageParam);
        return "region/region";
    }
    @PostMapping("/AddPlace")
    public String AddRobotType(String place_name, String gps_name,String lon,String lat){
        Place place = new Place();
        place.setPlace_name(place_name);
        place.setGps_name(gps_name);
        place.setLon(lon);
        place.setLat(lat);
        boolean flag = placeService.save(place);
        if (flag){
            return "redirect:/region/1/5";
        }else
        {
            return "redirect:/region/1/5";
        }
    }

    @PostMapping("/DeletePlace")
    public void DeleteRobotType(String id, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        List<Place> placeList = placeService.list(new QueryWrapper<Place>().orderByDesc("id"));
        if(placeList.size()<Integer.parseInt(id))
        {
            PrintWriter writer = response.getWriter();
            String msg = null;
            msg = "alert('删除失败');history.go(-1)";
            writer.print("<script type='text/javascript'>" + msg + "</script>");
            writer.flush();
            writer.close();
        }else {
            Place obj = placeList.get(Integer.parseInt(id) - 1);
            boolean flag = placeService.remove(new QueryWrapper<Place>().eq("id", obj.getId()));
            if (flag) {
                PrintWriter writer = response.getWriter();
                String msg = null;
                msg = "alert('删除成功');location.href='/region/1/5'";;
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
