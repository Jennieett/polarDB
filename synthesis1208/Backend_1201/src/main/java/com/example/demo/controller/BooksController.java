package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.model.vo.BooksVO;
import com.example.demo.model.vo.borrowbookVO;
import com.example.demo.service.BooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/Books")
public class BooksController {

    @Autowired
    private BooksService booksService;


    //查询全部
    @CrossOrigin
    @GetMapping("/getAll")
    public R<List<BooksVO>> getAll() {
        List<BooksVO> response = booksService.getAll();
        return R.ok(response, "查询成功！");
    }

    //按一个值查询
    @CrossOrigin
    @GetMapping("/getBySingleFilter")
    public R<List<BooksVO>> getBySingleFilter(@RequestParam String key, @RequestParam String value) {
        List<BooksVO> response = booksService.getBySingleFilter(key, value);
        return R.ok(response, "查询成功！");
    }

    //按一个值查询+借阅
    @CrossOrigin
    @GetMapping("/getBySingleFilter1")
    public R<List<borrowbookVO>> getBySingleFilter1(@RequestParam String key, @RequestParam String value) {
        List<borrowbookVO> response = booksService.getBySingleFilter1(key, value);
        return R.ok(response, "查询成功！");
    }

    //按两个值查询
    @GetMapping("/getByDoubleFilters")
    public R<List<BooksVO>> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<BooksVO> response = booksService.getByDoubleFilters(key1, value1, key2, value2);
        return R.ok(response, "查询成功！");
    }




    //新增一条记录
    @GetMapping("/newTuple")
    public R<String> newTuple(String id, String name, String year, String type, String author, String publisher){
        Integer i = booksService.newTuple(id, name, year, type, author, publisher);
        String response = (i==1) ? "已新增" : "未新增";
        String msg = (i==1) ? "新增成功！" : "数据未新增，但无异常";
        return R.ok(response, msg);
    }

    //修改一条记录
    @GetMapping("/updateTuple")
    public R<String> updateTuple(String oldId, String id, String name, String year, String type, String author, String publisher){
        Integer i = booksService.updateTuple(oldId, id, name, year, type, author, publisher);
        String response = (i==1) ? "已修改" : "未修改";
        String msg = (i==1) ? "修改成功！" : "数据未修改，但无异常";
        return R.ok(response, msg);
    }

    //删除一条记录
    @CrossOrigin
    @GetMapping("/deleteTuple")
    public R<String> deleteTuple(@RequestParam String id){
        Integer i = booksService.deleteTuple(id);
        String response = (i==1) ? "已删除" : "未删除";
        String msg = (i==1) ? "删除成功！" : "数据未删除，但无异常";
        return R.ok(response, msg);
    }

}