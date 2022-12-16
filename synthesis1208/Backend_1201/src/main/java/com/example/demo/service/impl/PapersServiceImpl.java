package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.PapersMapper;
import com.example.demo.model.entity.Papers;
import com.example.demo.model.vo.PapersVO;
import com.example.demo.service.PapersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class PapersServiceImpl extends ServiceImpl<PapersMapper, Papers> implements PapersService {

    @Autowired
    private PapersMapper papersMapper;


    //查询全部
    @Override
    public List<PapersVO> getAll() {
        List<PapersVO> result = new ArrayList<>();
        List<Papers> list = papersMapper.getAll();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Papers piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                Integer ino = piece.getIssueNo();
                Integer pno = piece.getPageNo();
                result.add(new PapersVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p).setIssueNo(ino).setPageNo(pno));
            }
        }
        //返回结果
        return result;
    }



    //按一个值查询
    @Override
    public List<PapersVO> getBySingleFilter(String key, String value) {
        List<PapersVO> result = new ArrayList<>();
        List<Papers> list = papersMapper.getBySingleFilter(key,value);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Papers piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                Integer ino = piece.getIssueNo();
                Integer pno = piece.getPageNo();
                result.add(new PapersVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p).setIssueNo(ino).setPageNo(pno));
            }
        }
        //返回结果
        return result;
    }




    //按两个值查询
    @Override
    public List<PapersVO> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<PapersVO> result = new ArrayList<>();
        List<Papers> list = papersMapper.getByDoubleFilters(key1, value1, key2, value2);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Papers piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                Integer ino = piece.getIssueNo();
                Integer pno = piece.getPageNo();
                result.add(new PapersVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p).setIssueNo(ino).setPageNo(pno));
            }
        }
        //返回结果
        return result;
    }




    //增删改
    @Override
    public Integer newTuple(String id, String name, String year, String type, String author, String publisher, String issueNo, String pageNo){
        Integer i = papersMapper.newTuple(id, name, year, type, author, publisher, issueNo, pageNo);
        return i;
    }

    @Override
    public Integer updateTuple(String oldId, String id, String name, String year, String type, String author, String publisher, String issueNo, String pageNo){
        Integer i = papersMapper.updateTuple(oldId, id, name, year, type, author, publisher, issueNo, pageNo);
        return i;
    }

    @Override
    public Integer deleteTuple(String id){
        Integer i = papersMapper.deleteTuple(id);
        return i;
    }

}