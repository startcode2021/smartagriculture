package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Journal;
import com.community.service.JournalService;
import com.community.vo.Msg;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JournalController {

    @Autowired
    JournalService journalService;

    @GetMapping("/journal/{page}/{limit}")
    public String region(@PathVariable int page, @PathVariable int limit, Model model) {
        if (page < 1){
            page = 1;
        }
        Page<Journal> pageParam = new Page<>(page, limit);
        journalService.page(pageParam, new QueryWrapper<Journal>().orderByDesc("id"));
        List<Journal> journals = pageParam.getRecords();
        model.addAttribute("Journals",journals);
        model.addAttribute("pageParam",pageParam);
        return "journal/journal";
    }
    @PostMapping("/AddJournal")
    public String AddJournal(String title, String Content)
    {
        Journal journal = new Journal();
        journal.setTitle(title);
        journal.setContent(Content);
        DateTime t = new DateTime();
        journal.setAdd_time(t.toString());
        boolean flag = journalService.save(journal);
        return "redirect:/journal/1/5";
    }
    @PostMapping("/DelJournal")
    @ResponseBody
    public Msg DelJournal(int id)
    {
        boolean flag = journalService.remove(new QueryWrapper<Journal>().eq("id",id));
        if(flag)
        {
            return Msg.success();
        }else{
            return Msg.error();
        }
    }
    @PostMapping("/ViewContent")
    public String ViewContent(int id){
        return "journal/journalcontent";
    }
}
