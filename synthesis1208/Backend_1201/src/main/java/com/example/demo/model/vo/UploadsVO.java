package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
public class UploadsVO {

    private String recordId;

    private String paperId;

    private String userId;

    private String dateTime;

}