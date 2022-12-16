package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;



@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    //查询
    List<Users> getAll();
    List<Users> getBySingleFilter(@Param("key") String key, @Param("value") String value);
    List<Users> getByDoubleFilters(@Param("key1") String key1, @Param("value1") String value1, @Param("key2") String key2, @Param("value2") String value2);

    //增删改
    Integer newTuple(@Param("id") String id, @Param("name") String name, @Param("type") String type, @Param("password") String passowrd, @Param("dept") String dept, @Param("tel") String tel);
    Integer updateTuple(@Param("oldId") String oldId, @Param("id") String id, @Param("name") String name, @Param("type") String type, @Param("password") String passowrd, @Param("dept") String dept, @Param("tel") String tel);
    Integer deleteTuple(@Param("id") String id);

}
