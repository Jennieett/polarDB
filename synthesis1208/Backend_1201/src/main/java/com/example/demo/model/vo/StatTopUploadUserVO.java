package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StatTopUploadUserVO {

    private String userId;

    private String userName;

    private Integer paperCount;

}
