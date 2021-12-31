package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Place;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class JournalController {

    @GetMapping("/journal/{page}/{limit}")
    public String region(@PathVariable int page, @PathVariable int limit, Model model) {
        if (page < 1){
            page = 1;
        }
        return "journal/journal";
    }
    @PostMapping("/AddJournal")
    public void AddJournal()
    {

    }
}
