package com.bjpowernode.mapper;

import com.bjpowernode.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentMapper {

    public StudentMapper() {
        System.out.println("StudentMapper====" + this);
    }

    public void add(Student student){
        System.out.println("添加成功====" + student);

    }

}
