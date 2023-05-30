package com.bjpowernode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoom {
    private Integer id;
    private String classname;
    private List<Student> list;
}
