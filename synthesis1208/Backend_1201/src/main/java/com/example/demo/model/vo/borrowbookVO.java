package com.example.demo.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class borrowbookVO {
    private String id;

    private String name;

    private Integer year;

    private String type;

    private String author;

    private String publisher;

    private String recordId;

    @Override
    public String toString() {
        return "borrowbookVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", recordId='" + recordId + '\'' +
                '}';
    }
}
