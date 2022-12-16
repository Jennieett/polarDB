package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.model.vo.UploadsVO;
import com.example.demo.service.UploadsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;




@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/Uploads")
public class UploadsController {

    @Autowired
    private UploadsService uploadsService;


    //查询全部
    @GetMapping("/getAll")
    public R<List<UploadsVO>> getAll(){
        List<UploadsVO> response = uploadsService.getAll();
        return R.ok(response, "查询成功！");
    }

    //按一个值查询
    @GetMapping("/getBySingleFilter")
    public R<List<UploadsVO>> getBySingleFilter(String key, String value) {
        List<UploadsVO> response = uploadsService.getBySingleFilter(key, value);
        return R.ok(response, "查询成功！");
    }

    //按两个值查询
    @GetMapping("/getByDoubleFilters")
    public R<List<UploadsVO>> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<UploadsVO> response = uploadsService.getByDoubleFilters(key1, value1, key2, value2);
        return R.ok(response, "查询成功！");
    }




    //注意参数dateTime不可以不传，而且必须为：2000-01-10T00:00:00格式
    //新增一条记录
    @GetMapping("/newTuple")
    public R<String> newTuple(String recordId, String paperId, String userId, String dateTime){
        LocalDateTime t = LocalDateTime.parse(dateTime);
        Integer i = uploadsService.newTuple(recordId, paperId, userId, t);
        String response = (i==1) ? "已新增" : "未新增";
        String msg = (i==1) ? "新增成功！" : "数据未新增，但无异常";
        return R.ok(response, msg);
    }

    //修改一条记录
    @GetMapping("/updateTuple")
    public R<String> updateTuple(String oldRecordId, String recordId, String paperId, String userId, String dateTime){
        LocalDateTime t = LocalDateTime.parse(dateTime);
        Integer i = uploadsService.updateTuple(oldRecordId, recordId, paperId, userId, t);
        String response = (i==1) ? "已修改" : "未修改";
        String msg = (i==1) ? "修改成功！" : "数据未修改，但无异常";
        return R.ok(response, msg);
    }

    //删除一条记录
    @GetMapping("/deleteTuple")
    public R<String> deleteTuple(String recordId){
        Integer i = uploadsService.deleteTuple(recordId);
        String response = (i==1) ? "已删除" : "未删除";
        String msg = (i==1) ? "删除成功！" : "数据未删除，但无异常";
        return R.ok(response, msg);
    }

}


