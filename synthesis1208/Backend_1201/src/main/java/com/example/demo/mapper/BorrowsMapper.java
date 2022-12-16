package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.Borrows;
import com.example.demo.model.vo.allborrowVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface BorrowsMapper extends BaseMapper<Borrows> {

    //查询
    List<Borrows> getAll();
    List<Borrows> getBySingleFilter(@Param("key") String key, @Param("value") String value);
    List<Borrows> getByDoubleFilters(@Param("key1") String key1, @Param("value1") String value1, @Param("key2") String key2, @Param("value2") String value2);

    //增删改
    //UPDATE语句和INSERT INTO语句默认都返回int，改成Integer会减少一些意外错误
    Integer newTuple(@Param("recordId") String recordId, @Param("bookId") String bookId, @Param("userId") String userId, @Param("dateTime") LocalDateTime dateTime, @Param("returned") String returned);
    Integer updateTuple(@Param("oldRecordId") String oldRecordId, @Param("recordId") String recordId, @Param("bookId") String bookId, @Param("userId") String userId, @Param("dateTime") LocalDateTime dateTime, @Param("returned") String returned);
    Integer deleteTuple(@Param("recordId") String recordId);

    Integer maxTuple();

    List<allborrowVO> getBySingleFilter1(String key, String value);

    List<allborrowVO> getAll1();
}