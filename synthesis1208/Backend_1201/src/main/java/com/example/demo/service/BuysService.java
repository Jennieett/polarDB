package com.example.demo.service;

import com.example.demo.model.vo.BuysVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BuysService {

    public List<BuysVO> getAll();
    public List<BuysVO> getBySingleFilter(String key, String value);
    public List<BuysVO> getByDoubleFilters(String key1, String value1, String key2, String value2);

    public Integer newTuple(String recordId, String bookId, String userId, LocalDateTime dateTime);
    public Integer updateTuple(String oldRecordId, String recordId, String bookId, String userId, LocalDateTime dateTime);
    public Integer deleteTuple(String recordId);

}
