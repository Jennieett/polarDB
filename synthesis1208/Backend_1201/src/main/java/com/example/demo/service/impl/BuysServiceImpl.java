package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.BuysMapper;
import com.example.demo.model.entity.Buys;
import com.example.demo.model.vo.BuysVO;
import com.example.demo.service.BuysService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class BuysServiceImpl extends ServiceImpl<BuysMapper, Buys> implements BuysService {

    @Autowired
    private BuysMapper buysMapper;


    //查询全部
    @Override
    public List<BuysVO> getAll() {
        List<BuysVO> result = new ArrayList<>();
        List<Buys> list = buysMapper.getAll();
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Buys piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                result.add(new BuysVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t));
            }
        }
        //返回结果
        return result;
    }


    //按一个值查询
    @Override
    public List<BuysVO> getBySingleFilter(String key, String value) {
        List<BuysVO> result = new ArrayList<>();
        List<Buys> list = buysMapper.getBySingleFilter(key,value);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Buys piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                result.add(new BuysVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t));
            }
        }
        //返回结果
        return result;
    }


    //按两个值查询
    @Override
    public List<BuysVO> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<BuysVO> result = new ArrayList<>();
        List<Buys> list = buysMapper.getByDoubleFilters(key1, value1, key2, value2);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Buys piece : list) {
                String r = piece.getRecordId();
                String b = piece.getBookId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                result.add(new BuysVO().setRecordId(r).setBookId(b).setUserId(u).setDateTime(t));
            }
        }
        //返回结果
        return result;
    }




    //增删改
    @Override
    public Integer newTuple(String recordId, String bookId, String userId, LocalDateTime dateTime){
        Integer i = buysMapper.newTuple(recordId, bookId, userId, dateTime);
        return i;
    }

    @Override
    public Integer updateTuple(String oldRecordId, String recordId, String bookId, String userId, LocalDateTime dateTime){
        Integer i = buysMapper.updateTuple(oldRecordId, recordId, bookId, userId, dateTime);
        return i;
    }

    @Override
    public Integer deleteTuple(String recordId){
        Integer i = buysMapper.deleteTuple(recordId);
        return i;
    }


}
