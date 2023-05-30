package com.bjpowernode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private Integer id;
    private Integer cid;
    private String name;
    private Integer age;
    private Integer isdeleted;
    private ClassRoom classRoom;

    public Student(Integer id) {
        this.id = id;
    }
}
