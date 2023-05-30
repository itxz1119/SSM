package com.bjpowernode.mapper;

import com.bjpowernode.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list();

    Student findById(int id);

    void updateMoney(Student student);
}
