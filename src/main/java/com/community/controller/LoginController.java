package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.dao.pojo.User;
import com.community.service.UserService;
import com.community.vo.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录页面")
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ApiOperation(value = "登录失败跳转")
    @GetMapping("/toLoginfail")
    public String toLoginfail(){
        return "login_fail";
    }

    @ApiOperation(value = "注册页面")
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }

    @ApiOperation(value = "手机号是否重复")
    @PostMapping("/havephone")
    @ResponseBody
    public Msg Havephone(String phone)
    {
        User re = userService.getOne(new QueryWrapper<User>().eq("phone", phone));
        if(re != null){
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("phone", "手机号已被注册");
            return Msg.error().add("map", map2);
        }else
        {
            return Msg.success();
        }
    }

    @ApiOperation(value = "注册表单提交")
    @PostMapping("/register")
    @ResponseBody
    public Msg Register(String name, String password,String phone,String email) {
        //判断该用户名是否已被注册
        User re = userService.getOne(new QueryWrapper<User>().eq("phone", phone));

        if (re != null) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("phone", "手机号已被注册");
            return Msg.error().add("map", map2);
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setEmail(email);
            user.setPhone(phone);
            //1管理员:直接修改数据库的用户，只能打开mySQL改的
            //2普通会员:管理员对游客进行升级
            //3游客:刚注册的用户
            user.setRoleid(3);
            boolean flag = userService.save(user);
            if (flag) {
                return Msg.success();
            } else {
                return Msg.error();
            }
        }
    }
}
