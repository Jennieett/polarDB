package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BooksVO {

    private String id;

    private String name;

    private Integer year;

    private String type;

    private String author;

    private String publisher;


}