package com.community.dao.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    //权限等级
    private int role_id;
}
