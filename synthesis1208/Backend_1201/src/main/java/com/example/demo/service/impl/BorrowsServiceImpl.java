package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.demo.mapper.BorrowsMapper;
import com.example.demo.model.entity.Borrows;
import com.example.demo.model.vo.BorrowsVO;
import com.example.demo.model.vo.allborrowVO;
import com.example.demo.service.BorrowsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class BorrowsServiceImpl extends ServiceImpl<BorrowsMapper, Borrows> implements BorrowsService {

    @Autowired
    private BorrowsMapper borrowsMapper;


    //查询全部
    @Override
    public List<BorrowsVO> getAll() {
        List<BorrowsVO> result = new ArrayList<>();
        List<Borrows> list = borrowsMapper.getAll();
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Borrows piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                Boolean rt = piece.getReturned();
                result.add(new BorrowsVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t).setReturned(rt));
            }
        }
        //返回结果
        return result;
    }

    //getall+bookname
    public List<allborrowVO> getAll1() {
        List<allborrowVO> result = new ArrayList<>();
        List<allborrowVO> list = borrowsMapper.getAll1();
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(allborrowVO piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                Boolean rt = piece.getReturned();
                String bn=piece.getBookname();
                result.add(new allborrowVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t).setReturned(rt).setBookname(bn));
            }
        }
        //返回结果
        return result;
    }


    //按一个值查询
    @Override
    public List<BorrowsVO> getBySingleFilter(String key, String value) {
        List<BorrowsVO> result = new ArrayList<>();
        List<Borrows> list = borrowsMapper.getBySingleFilter(key,value);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Borrows piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                Boolean rt = piece.getReturned();
                result.add(new BorrowsVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t).setReturned(rt));
            }
        }
        //返回结果
        return result;
    }


    //按两个值查询
    @Override
    public List<BorrowsVO> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<BorrowsVO> result = new ArrayList<>();
        List<Borrows> list = borrowsMapper.getByDoubleFilters(key1, value1, key2, value2);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Borrows piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                Boolean rt = piece.getReturned();
                result.add(new BorrowsVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t).setReturned(rt));
            }
        }
        //返回结果
        return result;
    }




    //增删改
    @Override
    public Integer newTuple(String recordId, String bookId, String userId, LocalDateTime dateTime, String returned){
        Integer i = borrowsMapper.newTuple(recordId, bookId, userId, dateTime, returned);
        return i;
    }

    @Override
    public Integer updateTuple(String oldRecordId, String recordId, String bookId, String userId, LocalDateTime dateTime, String returned){
        Integer i = borrowsMapper.updateTuple(oldRecordId, recordId, bookId, userId, dateTime, returned);
        return i;
    }

    @Override
    public Integer deleteTuple(String recordId){
        Integer i = borrowsMapper.deleteTuple(recordId);
        return i;
    }

    public Integer maxTuple(){
        Integer i = borrowsMapper.maxTuple();
        return i;
    }

    public List<allborrowVO> getBySingleFilter1(String key, String value){
        List<allborrowVO> result = new ArrayList<>();
        List<allborrowVO> list = borrowsMapper.getBySingleFilter1(key,value);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(allborrowVO piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                Boolean rt = piece.getReturned();
                String bn=piece.getBookname();
                result.add(new allborrowVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t).setReturned(rt).setBookname(bn));
            }
        }
        //返回结果
        return result;
    }
}

