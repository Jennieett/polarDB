package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.model.vo.UsersVO;
import com.example.demo.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    //查询全部
    @GetMapping("/getAll")
    public R<List<UsersVO>> getAll() {
        List<UsersVO> response = usersService.getAll();
        return R.ok(response, "查询成功！");
    }

    //按一个值查询
    @GetMapping("/getBySingleFilter")
    public R<List<UsersVO>> getBySingleFilter(String key, String value) {
        List<UsersVO> response = usersService.getBySingleFilter(key, value);
        return R.ok(response, "查询成功！");
    }

    //按两个值查询
    @GetMapping("/getByDoubleFilters")
    public R<List<UsersVO>> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<UsersVO> response = usersService.getByDoubleFilters(key1, value1, key2, value2);
        return R.ok(response, "查询成功！");
    }




    //新增一条记录
    @GetMapping("/newTuple")
    public R<String> newTuple(String id, String name, String type, String password, String dept, String tel){
        Integer i = usersService.newTuple(id, name, type, password, dept, tel);
        String response = (i==1) ? "已新增" : "未新增";
        String msg = (i==1) ? "新增成功！" : "数据未新增，但无异常";
        return R.ok(response, msg);
    }

    //修改一条记录
    @GetMapping("/updateTuple")
    public R<String> updateTuple(String oldId, String id, String name, String type, String password, String dept, String tel){
        Integer i = usersService.updateTuple(oldId, id, name, type, password, dept, tel);
        String response = (i==1) ? "已修改" : "未修改";
        String msg = (i==1) ? "修改成功！" : "数据未修改，但无异常";
        return R.ok(response, msg);
    }

    //删除一条记录
    @GetMapping("/deleteTuple")
    public R<String> deleteTuple(String id){
        Integer i = usersService.deleteTuple(id);
        String response = (i==1) ? "已删除" : "未删除";
        String msg = (i==1) ? "删除成功！" : "数据未删除，但无异常";
        return R.ok(response, msg);
    }

}
