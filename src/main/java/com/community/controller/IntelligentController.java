package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntelligentController {
    @GetMapping("/intelligent")
    public String intelligent(){
        return "intelligentcontrol/intelligentcontrol";
    }


}
