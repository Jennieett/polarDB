package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.Uploads;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface UploadsMapper extends BaseMapper<Uploads> {

    //查询
    List<Uploads> getAll();
    List<Uploads> getBySingleFilter(@Param("key") String key, @Param("value") String value);
    List<Uploads> getByDoubleFilters(@Param("key1") String key1, @Param("value1") String value1, @Param("key2") String key2, @Param("value2") String value2);

    //增删改
    Integer newTuple(@Param("recordId") String recordId, @Param("paperId") String paperId, @Param("userId") String userId, @Param("dateTime") LocalDateTime dateTime);
    Integer updateTuple(@Param("oldRecordId") String oldRecordId, @Param("recordId") String recordId, @Param("paperId") String paperId, @Param("userId") String userId, @Param("dateTime") LocalDateTime dateTime);
    Integer deleteTuple(@Param("recordId") String recordId);

}