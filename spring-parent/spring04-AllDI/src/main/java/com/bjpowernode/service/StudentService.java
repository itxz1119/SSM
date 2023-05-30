package com.bjpowernode.service;

import com.bjpowernode.entity.Student;
import com.github.pagehelper.PageInfo;

public interface StudentService {
    PageInfo<Student> pageList();

    int updateStudent(int id);

    void transfer(int fromId, int toId, double money);
}
