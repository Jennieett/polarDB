package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.model.vo.BorrowsVO;
import com.example.demo.model.vo.allborrowVO;
import com.example.demo.service.BorrowsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;



@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/Borrows")
public class BorrowsController {

    @Autowired
    private BorrowsService borrowsService;


    //查询全部
    @GetMapping("/getAll")
    public R<List<BorrowsVO>> getAll(){
        List<BorrowsVO> response = borrowsService.getAll();
        return R.ok(response, "查询成功！");
    }

    //查询全部+bookname
    @GetMapping("/getAll1")
    public R<List<allborrowVO>> getAll1(){
        List<allborrowVO> response = borrowsService.getAll1();
        return R.ok(response, "查询成功！");
    }

    //按一个值查询
    @GetMapping("/getBySingleFilter")
    public R<List<BorrowsVO>> getBySingleFilter(String key, String value) {
        List<BorrowsVO> response = borrowsService.getBySingleFilter(key, value);
        return R.ok(response, "查询成功！");
    }

    //单值查询+图书名称
    @GetMapping("/getBySingleFilter1")
    public R<List<allborrowVO>> getBySingleFilter1(String key, String value) {
        List<allborrowVO> response = borrowsService.getBySingleFilter1(key, value);
        return R.ok(response, "查询成功！");
    }

    //按两个值查询
    @GetMapping("/getByDoubleFilters")
    public R<List<BorrowsVO>> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<BorrowsVO> response = borrowsService.getByDoubleFilters(key1, value1, key2, value2);
        return R.ok(response, "查询成功！");
    }




    //注意参数dateTime不可以不传，而且必须为：2000-01-10T00:00:00格式
    //新增一条记录
    @GetMapping("/newTuple")
    public R<String> newTuple(String recordId, String bookId, String userId, String dateTime, String returned){
        LocalDateTime t = LocalDateTime.parse(dateTime);
        Integer i = borrowsService.newTuple(recordId, bookId, userId, t, returned);
        String response = (i==1) ? "已新增" : "未新增";
        String msg = (i==1) ? "新增成功！" : "数据未新增，但无异常";
        return R.ok(response, msg);
    }

    //修改一条记录
    @GetMapping("/updateTuple")
    public R<String> updateTuple(String oldRecordId, String recordId, String bookId, String userId, String dateTime, String returned){
        LocalDateTime t = LocalDateTime.parse(dateTime);
        Integer i = borrowsService.updateTuple(oldRecordId, recordId, bookId, userId, t, returned);
        String response = (i==1) ? "已修改" : "未修改";
        String msg = (i==1) ? "修改成功！" : "数据未修改，但无异常";
        return R.ok(response, msg);
    }

    //删除一条记录
    @GetMapping("/deleteTuple")
    public R<String> deleteTuple(String recordId){
        Integer i = borrowsService.deleteTuple(recordId);
        String response = (i==1) ? "已删除" : "未删除";
        String msg = (i==1) ? "删除成功！" : "数据未删除，但无异常";
        return R.ok(response, msg);
    }

    @GetMapping("/maxTuple")
    public R<Integer> maxTuple(){
        Integer i = borrowsService.maxTuple();
        String response = String.valueOf(i);
        String msg = (i!=null) ? "查询成功！" : "未查到最大值";
        return R.ok(i, msg);
    }

}



