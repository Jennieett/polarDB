package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;



@Getter
@Setter
@Accessors(chain = true)
public class BuysVO {

    private String recordId;

    private String bookId;

    private String userId;

    private String dateTime;

}
