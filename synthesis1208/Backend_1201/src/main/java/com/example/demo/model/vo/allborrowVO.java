package com.example.demo.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class allborrowVO {
    private String recordId;

    private String bookId;

    private String userId;

    private String dateTime;

    private Boolean returned;

    private String bookname;

//    @Override
//    public String toString() {
//        return "borrowbookVO{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", year=" + year +
//                ", type='" + type + '\'' +
//                ", author='" + author + '\'' +
//                ", publisher='" + publisher + '\'' +
//                ", recordId='" + recordId + '\'' +
//                '}';
//    }
}

