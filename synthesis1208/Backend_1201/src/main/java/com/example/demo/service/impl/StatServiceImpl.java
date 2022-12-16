package com.example.demo.service.impl;


import com.example.demo.mapper.StatMapper;
import com.example.demo.model.vo.StatTopAuthorVO;
import com.example.demo.model.vo.StatTopBorrowDeptVO;
import com.example.demo.model.vo.StatTopBuyPublisherVO;
import com.example.demo.model.vo.StatTopUploadUserVO;
import com.example.demo.service.StatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper statMapper;

    //借书学院排名
    @Override
    public List<StatTopBorrowDeptVO> topBorrowDept(){

        List<StatTopBorrowDeptVO> result = new ArrayList<>();
        List<StatTopBorrowDeptVO> list = statMapper.topBorrowDept();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (StatTopBorrowDeptVO piece : list) {
                String d = piece.getDeptName();
                Integer b = piece.getBookCount();
                Integer s = piece.getStudentCount();
                result.add(new StatTopBorrowDeptVO().setDeptName(d).setBookCount(b).setStudentCount(s));
            }
        }
        //返回结果
        return result;
    }


    //上传用户排行
    @Override
    public List<StatTopUploadUserVO> topUploadUser(){
        List<StatTopUploadUserVO> result = new ArrayList<>();
        List<StatTopUploadUserVO> list = statMapper.topUploadUser();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (StatTopUploadUserVO piece : list) {
                String id = piece.getUserId();
                String n = piece.getUserName();
                Integer p = piece.getPaperCount();
                result.add(new StatTopUploadUserVO().setUserId(id).setUserName(n).setPaperCount(p));
            }
        }
        //返回结果
        return result;
    }



    //出版社销量排名
    @Override
    public List<StatTopBuyPublisherVO> topBuyPublisher(){

        List<StatTopBuyPublisherVO> result = new ArrayList<>();
        List<StatTopBuyPublisherVO> list = statMapper.topBuyPublisher();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (StatTopBuyPublisherVO piece : list) {
                String n = piece.getPublisherName();
                Integer s = piece.getSaleCount();
                Integer p = piece.getPublishCount();
                result.add(new StatTopBuyPublisherVO().setPublisherName(n).setSaleCount(s).setPublishCount(p));
            }
        }
        //返回结果
        return result;
    }





    //最高产作者
    @Override
    public List<StatTopAuthorVO> topAuthor(){
        List<StatTopAuthorVO> result = new ArrayList<>();
        List<StatTopAuthorVO> list = statMapper.topAuthor();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (StatTopAuthorVO piece : list) {
                String a = piece.getAuthor();
                Integer s = piece.getSum();
                Integer b = piece.getBookCount();
                Integer p = piece.getPaperCount();
                result.add(new StatTopAuthorVO().setAuthor(a).setSum(s).setBookCount(b).setPaperCount(p));
            }
        }
        //返回结果
        return result;
    }

}
