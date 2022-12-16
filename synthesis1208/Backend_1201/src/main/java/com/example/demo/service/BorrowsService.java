package com.example.demo.service;

import com.example.demo.model.vo.BorrowsVO;
import com.example.demo.model.vo.allborrowVO;

import java.time.LocalDateTime;
import java.util.List;

public interface BorrowsService {

    public List<BorrowsVO> getAll();
    public List<BorrowsVO> getBySingleFilter(String key, String value);
    public List<BorrowsVO> getByDoubleFilters(String key1, String value1, String key2, String value2);

    public Integer newTuple(String recordId, String bookId, String userId, LocalDateTime dateTime, String returned);
    public Integer updateTuple(String oldRecordId, String recordId, String bookId, String userId, LocalDateTime dateTime, String returned);
    public Integer deleteTuple(String recordId);

    public Integer maxTuple();

    public List<allborrowVO> getBySingleFilter1(String key, String value);

    public List<allborrowVO> getAll1();
}
