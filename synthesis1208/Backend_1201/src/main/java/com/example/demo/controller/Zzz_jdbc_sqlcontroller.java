package com.example.demo.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller

public class Zzz_jdbc_sqlcontroller {

    @Resource
    JdbcTemplate jdbcTemplate;


    @ResponseBody
    @GetMapping("/")
    public List<Map<String, Object>> map() {
        System.out.println("list:");
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM buys;");
        System.out.println(list);
        return list;
    }
}
