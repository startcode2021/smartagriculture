package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.RobotType;
import com.community.service.RobotTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Bean;
import java.util.List;

@Controller
public class IntelligentController {

    @Autowired
    RobotTypeService robotTypeService;

    @GetMapping("/intelligent/{page}/{limit}")
    public String intelligent(@PathVariable int page, @PathVariable int limit, Model model){
        if (page < 1){
            page = 1;
        }
        Page<RobotType> pageParam = new Page<>(page, limit);
        robotTypeService.page(pageParam,new QueryWrapper<RobotType>().orderByDesc("add_date"));

        List<RobotType> RobotTypeList = pageParam.getRecords();
        model.addAttribute("RobotTypeList",RobotTypeList);
        model.addAttribute("pageParam",pageParam);

        return "intelligentcontrol/intelligentcontrol";
    }


}
