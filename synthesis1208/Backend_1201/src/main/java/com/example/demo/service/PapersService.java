package com.example.demo.service;

import com.example.demo.model.vo.PapersVO;

import java.util.List;

public interface PapersService {

    public List<PapersVO> getAll();
    public List<PapersVO> getBySingleFilter(String key, String value);
    public List<PapersVO> getByDoubleFilters(String key1, String value1, String key2, String value2);

    public Integer newTuple(String id, String name, String year, String type, String author, String publisher, String issueNo, String pageNo);
    public Integer updateTuple(String oldId, String id, String name, String year, String type, String author, String publisher, String issueNo, String pageNo);
    public Integer deleteTuple(String id);

}
