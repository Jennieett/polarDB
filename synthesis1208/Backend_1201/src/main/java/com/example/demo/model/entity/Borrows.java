package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("borrows")
public class Borrows implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recordId", type = IdType.AUTO)
    private String recordId;


    @TableField("bookId")
    private String bookId;


    @TableField("userId")
    private String userId;


    @TableField(value = "dateTime", fill = FieldFill.INSERT)
    private LocalDateTime dateTime;


    @TableField("returned")
    private Boolean returned;



    @Override
    public String toString() {
        return "Borrows{" +
                "recordId=" + recordId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", returned=" + returned +
                '}';
    }

}