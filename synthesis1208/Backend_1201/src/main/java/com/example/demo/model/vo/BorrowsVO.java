package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BorrowsVO {

    private String recordId;

    private String bookId;

    private String userId;

    private String dateTime;

    private Boolean returned;

}

