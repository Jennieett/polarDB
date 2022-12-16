package com.example.demo.controller;


import com.example.demo.common.R;
import com.example.demo.model.vo.StatTopAuthorVO;
import com.example.demo.model.vo.StatTopBorrowDeptVO;
import com.example.demo.model.vo.StatTopBuyPublisherVO;
import com.example.demo.model.vo.StatTopUploadUserVO;
import com.example.demo.service.StatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/Stat")
public class StatController {

    @Autowired
    private StatService statService;

    //借书学院排名
    @CrossOrigin
    @GetMapping("/topBorrowDept")
    public R<List<StatTopBorrowDeptVO>> topBorrowDept() {
        List<StatTopBorrowDeptVO> response = statService.topBorrowDept();
        return R.ok(response, "查询成功！");
    }


    //上传用户排名
    @CrossOrigin
    @GetMapping("/topUploadUser")
    public R<List<StatTopUploadUserVO>> topUploadUser() {
        List<StatTopUploadUserVO> response = statService.topUploadUser();
        return R.ok(response, "查询成功！");
    }


    //出版社销量排名
    @CrossOrigin
    @GetMapping("/topBuyPublisher")
    public R<List<StatTopBuyPublisherVO>> topBuyPublisher() {
        List<StatTopBuyPublisherVO> response = statService.topBuyPublisher();
        return R.ok(response, "查询成功！");
    }


    //最高产作者
    @CrossOrigin
    @GetMapping("/topAuthor")
    public R<List<StatTopAuthorVO>> topAuthor() {
        List<StatTopAuthorVO> response = statService.topAuthor();
        return R.ok(response, "查询成功！");
    }



}
