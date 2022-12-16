package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UploadsMapper;
import com.example.demo.model.entity.Uploads;
import com.example.demo.model.vo.UploadsVO;
import com.example.demo.service.UploadsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class UploadsServiceImpl extends ServiceImpl<UploadsMapper, Uploads> implements UploadsService {

    @Autowired
    private UploadsMapper uploadsMapper;


    //查询全部
    @Override
    public List<UploadsVO> getAll() {
        List<UploadsVO> result = new ArrayList<>();
        List<Uploads> list = uploadsMapper.getAll();
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Uploads piece : list) {
                String r = piece.getRecordId();
                String p = piece.getPaperId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                result.add(new UploadsVO().setRecordId(r).setPaperId(p).setUserId(u).setDateTime(t));
            }
        }
        //返回结果
        return result;
    }


    //按一个值查询
    @Override
    public List<UploadsVO> getBySingleFilter(String key, String value) {
        List<UploadsVO> result = new ArrayList<>();
        List<Uploads> list = uploadsMapper.getBySingleFilter(key,value);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Uploads piece : list) {
                String r = piece.getRecordId();
                String p = piece.getPaperId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                result.add(new UploadsVO().setRecordId(r).setPaperId(p).setUserId(u).setDateTime(t));
            }
        }
        //返回结果
        return result;
    }


    //按两个值查询
    @Override
    public List<UploadsVO> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<UploadsVO> result = new ArrayList<>();
        List<Uploads> list = uploadsMapper.getByDoubleFilters(key1, value1, key2, value2);
        //若数据库不空，开始操作
        if(!CollectionUtils.isEmpty(list)){
            for(Uploads piece : list) {
                String r = piece.getRecordId();
                String p = piece.getPaperId();
                String u = piece.getUserId();
                String t = piece.getDateTime()==null ? "" : piece.getDateTime().toString();
                result.add(new UploadsVO().setRecordId(r).setPaperId(p).setUserId(u).setDateTime(t));
            }
        }
        //返回结果
        return result;
    }




    //增删改
    @Override
    public Integer newTuple(String recordId, String paperId, String userId, LocalDateTime dateTime){
        Integer i = uploadsMapper.newTuple(recordId, paperId, userId, dateTime);
        return i;
    }

    @Override
    public Integer updateTuple(String oldRecordId, String recordId, String paperId, String userId, LocalDateTime dateTime){
        Integer i = uploadsMapper.updateTuple(oldRecordId, recordId, paperId, userId, dateTime);
        return i;
    }

    @Override
    public Integer deleteTuple(String recordId){
        Integer i = uploadsMapper.deleteTuple(recordId);
        return i;
    }

}

