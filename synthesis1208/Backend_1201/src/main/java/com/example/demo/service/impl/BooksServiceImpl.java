package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.BooksMapper;
import com.example.demo.mapper.BorrowsMapper;
import com.example.demo.model.entity.Books;
import com.example.demo.model.vo.BooksVO;
import com.example.demo.model.vo.borrowbookVO;
import com.example.demo.service.BooksService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;


@Service
@AllArgsConstructor
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {

    @Autowired
    private BooksMapper booksMapper;
    private BorrowsMapper borrowsMapper;


    //查询全部
    @Override
    public List<BooksVO> getAll() {
        List<BooksVO> result = new ArrayList<>();
        List<Books> list = booksMapper.getAll();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Books piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                result.add(new BooksVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p));
            }
        }
        //返回结果
        return result;
    }


    //按一个值查询
    @Override
    public List<BooksVO> getBySingleFilter(String key, String value) {
        List<BooksVO> result = new ArrayList<>();
        List<Books> list = booksMapper.getBySingleFilter(key,value);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Books piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                result.add(new BooksVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p));
            }
        }
        //返回结果
        return result;
    }

    //结合借阅状态
    public List<borrowbookVO> getBySingleFilter1(String key, String value) {
        List<borrowbookVO> result = new ArrayList<>();
        List<borrowbookVO> list = booksMapper.getBySingleFilter1(key,value);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (borrowbookVO piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                String rec=piece.getRecordId();
                result.add(new borrowbookVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p).setRecordId(rec));
            }
        }
        //返回结果
        return result;
    }


    //按两个值查询
    @Override
    public List<BooksVO> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<BooksVO> result = new ArrayList<>();
        List<Books> list = booksMapper.getByDoubleFilters(key1, value1, key2, value2);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Books piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Integer y = piece.getYear();
                String t = piece.getType();
                String a = piece.getAuthor();
                String p = piece.getPublisher();
                result.add(new BooksVO().setId(id).setName(n).setYear(y).setType(t).setAuthor(a).setPublisher(p));
            }
        }
        //返回结果
        return result;
    }




    //增删改
    @Override
    public Integer newTuple(String id, String name, String year, String type, String author, String publisher){
        Integer i = booksMapper.newTuple(id, name, year, type, author, publisher);
        return i;
    }

    @Override
    public Integer updateTuple(String oldId, String id, String name, String year, String type, String author, String publisher){
        Integer i = booksMapper.updateTuple(oldId, id, name, year, type, author, publisher);
        return i;
    }

    @Override
    public Integer deleteTuple(String id){
        Integer i = booksMapper.deleteTuple(id);
        return i;
    }


    public Integer maxTuple(){
        Integer i = booksMapper.maxTuple();
        return i;
    }
}