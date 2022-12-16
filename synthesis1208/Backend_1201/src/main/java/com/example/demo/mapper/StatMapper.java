package com.example.demo.mapper;

import com.example.demo.model.vo.StatTopAuthorVO;
import com.example.demo.model.vo.StatTopBorrowDeptVO;
import com.example.demo.model.vo.StatTopBuyPublisherVO;
import com.example.demo.model.vo.StatTopUploadUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatMapper {

    List<StatTopBorrowDeptVO> topBorrowDept();
    List<StatTopUploadUserVO> topUploadUser();

    List<StatTopBuyPublisherVO> topBuyPublisher();

    List<StatTopAuthorVO> topAuthor();

}
