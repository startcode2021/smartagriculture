package com.community.controller;


import com.community.dao.pojo.Place;
import com.community.dao.pojo.User;
import com.community.service.PlaceService;
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

    @RequestMapping("/index/{Place}")
    public String index(@PathVariable String place,Model model){
        List<Place> placeList = placeService.list();
        model.addAttribute("PlaceList",placeList);
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}