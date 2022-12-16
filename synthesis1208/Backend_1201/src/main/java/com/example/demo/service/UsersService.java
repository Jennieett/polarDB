package com.example.demo.service;

import com.example.demo.model.vo.UsersVO;

import java.util.List;

public interface UsersService {

    public List<UsersVO> getAll();
    public List<UsersVO> getBySingleFilter(String key, String value);
    public List<UsersVO> getByDoubleFilters(String key1, String value1, String key2, String value2);

    public Integer newTuple(String id, String name, String type, String password, String dept, String tel);
    public Integer updateTuple(String oldId, String id, String name, String type, String password, String dept, String tel);
    public Integer deleteTuple(String id);

}
