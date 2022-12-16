package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StatTopBorrowDeptVO {

    private String deptName;

    private Integer bookCount;

    private Integer studentCount;

}
