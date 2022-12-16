package com.example.demo.service;

import com.example.demo.model.vo.StatTopAuthorVO;
import com.example.demo.model.vo.StatTopBorrowDeptVO;
import com.example.demo.model.vo.StatTopBuyPublisherVO;
import com.example.demo.model.vo.StatTopUploadUserVO;

import java.util.List;

public interface StatService {

    public List<StatTopBorrowDeptVO> topBorrowDept();
    public List<StatTopUploadUserVO> topUploadUser();

    public List<StatTopBuyPublisherVO> topBuyPublisher();

    public List<StatTopAuthorVO> topAuthor();
}
