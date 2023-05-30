package com.bjpowernode.service.impl;

import com.bjpowernode.entity.Student;
import com.bjpowernode.mapper.StudentMapper;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    public StudentServiceImpl() {
        System.out.println("StudentServiceImpl=====");
    }

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }
}
