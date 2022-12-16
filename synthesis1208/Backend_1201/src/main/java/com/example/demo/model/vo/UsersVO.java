package com.example.demo.model.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UsersVO {

    private String id;

    private String name;

    private Boolean type;       //管理员为0；学生为1

    private String password;

    private String dept;

    private String tel;

}
