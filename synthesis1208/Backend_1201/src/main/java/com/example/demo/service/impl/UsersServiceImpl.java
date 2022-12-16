package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.entity.Users;
import com.example.demo.model.vo.UsersVO;
import com.example.demo.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;


    //查询全部
    @Override
    public List<UsersVO> getAll() {
        List<UsersVO> result = new ArrayList<>();
        List<Users> list = usersMapper.getAll();
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Users piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Boolean t = piece.getType();
                String p = piece.getPassword();
                String d = piece.getDept();
                String tel = piece.getTel();
                result.add(new UsersVO().setId(id).setName(n).setType(t).setPassword(p).setDept(d).setTel(tel));
            }
        }
        //返回结果
        return result;
    }



    //按一个值查询
    @Override
    public List<UsersVO> getBySingleFilter(String key, String value) {
        List<UsersVO> result = new ArrayList<>();
        List<Users> list = usersMapper.getBySingleFilter(key,value);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Users piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Boolean t = piece.getType();
                String p = piece.getPassword();
                String d = piece.getDept();
                String tel = piece.getTel();
                result.add(new UsersVO().setId(id).setName(n).setType(t).setPassword(p).setDept(d).setTel(tel));
            }
        }
        //返回结果
        return result;
    }




    //按两个值查询
    @Override
    public List<UsersVO> getByDoubleFilters(String key1, String value1, String key2, String value2) {
        List<UsersVO> result = new ArrayList<>();
        List<Users> list = usersMapper.getByDoubleFilters(key1, value1, key2, value2);
        //若数据库不空，开始操作
        if (!CollectionUtils.isEmpty(list)) {
            for (Users piece : list) {
                String id = piece.getId();
                String n = piece.getName();
                Boolean t = piece.getType();
                String p = piece.getPassword();
                String d = piece.getDept();
                String tel = piece.getTel();
                result.add(new UsersVO().setId(id).setName(n).setType(t).setPassword(p).setDept(d).setTel(tel));
            }
        }
        //返回结果
        return result;
    }




    //增删改
    @Override
    public Integer newTuple(String id, String name, String type, String password, String dept, String tel){
        Integer i = usersMapper.newTuple(id, name, type, password, dept, tel);
        return i;
    }

    @Override
    public Integer updateTuple(String oldId, String id, String name, String type, String password, String dept, String tel){
        Integer i = usersMapper.updateTuple(oldId, id, name, type, password, dept, tel);
        return i;
    }

    @Override
    public Integer deleteTuple(String id){
        Integer i = usersMapper.deleteTuple(id);
        return i;
    }

}