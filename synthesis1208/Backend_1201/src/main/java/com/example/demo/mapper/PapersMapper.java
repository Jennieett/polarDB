package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.Papers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PapersMapper extends BaseMapper<Papers> {

    //查询
    List<Papers> getAll();
    List<Papers> getBySingleFilter(@Param("key") String key, @Param("value") String value);
    List<Papers> getByDoubleFilters(@Param("key1") String key1, @Param("value1") String value1, @Param("key2") String key2, @Param("value2") String value2);

    //增删改
    //UPDATE语句和INSERT INTO语句默认都返回int，改成Integer会减少一些意外错误
    Integer newTuple(@Param("id") String id, @Param("name") String name, @Param("year") String year, @Param("type") String type, @Param("author") String author, @Param("publisher") String publisher, @Param("issueNo") String issueNo, @Param("pageNo") String pageNo);
    Integer updateTuple(@Param("oldId") String oldId, @Param("id") String id, @Param("name") String name, @Param("year") String year, @Param("type") String type, @Param("author") String author, @Param("publisher") String publisher, @Param("issueNo") String issueNo, @Param("pageNo") String pageNo);
    Integer deleteTuple(@Param("id") String id);

}
