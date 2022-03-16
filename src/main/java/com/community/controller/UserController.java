package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.dao.pojo.User;
import com.community.service.UserRoleService;
import com.community.service.UserService;
import com.community.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    HttpSession session;

    @GetMapping("/background/{page}/{limit}")
    public String  background(@PathVariable int page, @PathVariable int limit, Model model){
        User user = (User) session.getAttribute("loginUser");
        if (page < 1){
            page = 1;
        }
        Page<User> pageParam = new Page<>(page, limit);
        userService.page(pageParam, new QueryWrapper<User>().orderByDesc("id"));
        List<User> userList = pageParam.getRecords();
        List<String> Role_name = new ArrayList<>();
        for(int i = 0 ; i < userList.size(); i++){
            if(userList.get(i).getRoleid() == 1){
                Role_name.add("管理员");
            }else if(userList.get(i).getRoleid() == 2){
                Role_name.add("普通用户");
            }else if(userList.get(i).getRoleid() == 3){
                Role_name.add("游客");
            }
        }
        model.addAttribute("userList",userList);
        model.addAttribute("roleList",Role_name);
        model.addAttribute("pageParam",pageParam);
        if(user.getRoleid()==1)
            return "background/background";
        else
            return "nopermissions";
    }
    @PostMapping("/DelUser")
    @ResponseBody
    public Msg DelUser(int id){
        User user = (User) session.getAttribute("loginUser");
        User del_user = userService.getOne(new QueryWrapper<User>().eq("id",id));
        boolean flag = false;
        if(user.getRoleid() < del_user.getRoleid()){
            //注销删除用户
            flag = userService.remove(new QueryWrapper<User>().eq("id",id));
        }
        if(flag)
        {
            return Msg.success();
        }else{
            return Msg.error();
        }
    }
    @PostMapping("/ChangeUserRole")
    @ResponseBody
    public Msg ChangeUserRole(int id){
        User user = (User) session.getAttribute("loginUser");
        User changerole_user = userService.getOne(new QueryWrapper<User>().eq("id",id));
        if(changerole_user.getRoleid() == 2)
            changerole_user.setRoleid(3);
        else if(changerole_user.getRoleid() == 3)
            changerole_user.setRoleid(2);
        else
            return Msg.error();
        boolean flag = false;
        if(user.getRoleid() < changerole_user.getRoleid()){
            //更改role
            flag = userService.update(changerole_user,new UpdateWrapper<User>().eq("id",id));
        }
        if(flag)
        {
            return Msg.success();
        }else{
            return Msg.error();
        }
    }
}
