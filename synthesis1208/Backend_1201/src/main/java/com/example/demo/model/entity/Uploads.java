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
@TableName("uploads")
public class Uploads implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recordId", type = IdType.AUTO)
    private String recordId;


    @TableField("paperId")
    private String paperId;


    @TableField("userId")
    private String userId;


    @TableField(value = "dateTime", fill = FieldFill.INSERT)
    private LocalDateTime dateTime;


    @Override
    public String toString() {
        return "Uploads{" +
                "recordId=" + recordId +
                ", paperId=" + paperId +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                '}';
    }
}

