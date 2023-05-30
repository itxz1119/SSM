package com.bjpowernode.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private String username;
    private String pwd;
    //规范前端向后端传的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //后端向前端传的数据
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

}
