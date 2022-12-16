package com.example.demo.service;

import com.example.demo.model.vo.BooksVO;
import com.example.demo.model.vo.borrowbookVO;

import java.time.LocalDateTime;
import java.util.List;

public interface BooksService {

    public List<BooksVO> getAll();
    public List<BooksVO> getBySingleFilter(String key, String value);
    public List<borrowbookVO> getBySingleFilter1(String key, String value);
    public List<BooksVO> getByDoubleFilters(String key1, String value1, String key2, String value2);

    public Integer newTuple(String id, String name, String year, String type, String author, String publisher);
    public Integer updateTuple(String oldId, String id, String name, String year, String type, String author, String publisher);
    public Integer deleteTuple(String id);

    public Integer maxTuple();

}
