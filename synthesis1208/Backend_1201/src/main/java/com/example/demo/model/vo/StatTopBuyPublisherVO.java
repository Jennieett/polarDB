package com.example.demo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StatTopBuyPublisherVO {

    private String publisherName;

    private Integer saleCount;

    private Integer publishCount;
}
