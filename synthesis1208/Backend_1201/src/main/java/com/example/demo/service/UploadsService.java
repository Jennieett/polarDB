package com.example.demo.service;

import com.example.demo.model.vo.UploadsVO;

import java.time.LocalDateTime;
import java.util.List;

public interface UploadsService {
    public List<UploadsVO> getAll();
    public List<UploadsVO> getBySingleFilter(String key, String value);
    public List<UploadsVO> getByDoubleFilters(String key1, String value1, String key2, String value2);

    public Integer newTuple(String recordId, String paperId, String userId, LocalDateTime dateTime);
    public Integer updateTuple(String oldRecordId, String recordId, String paperId, String userId, LocalDateTime dateTime);
    public Integer deleteTuple(String recordId);
}
