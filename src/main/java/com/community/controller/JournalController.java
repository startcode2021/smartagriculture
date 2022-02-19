package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.Journal;
import com.community.service.JournalService;
import com.community.vo.Msg;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "获取日记列表")
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

    @ApiOperation(value = "获取对应日记内容")
    @GetMapping("/journal/journalcontent/{id}")
    public String content(@PathVariable int id,Model model){
       Journal journal = journalService.getOne(new QueryWrapper<Journal>().eq("id",id));
       model.addAttribute("CurrentJournal",journal);
       return "journal/journalcontent";
    }

    @ApiOperation(value = "添加日记")
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

    @ApiOperation(value = "删除对应日记")
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

    @GetMapping("/ViewContent")
    @ResponseBody
    public String ViewContent(int id){
        String data = "/journal/journalcontent/"+id;
        return data;
    }
}
