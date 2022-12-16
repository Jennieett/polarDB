package com.example.demo.model.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StatTopAuthorVO {

    private String author;

    private Integer sum;

    private Integer bookCount;

    private Integer paperCount;

}
