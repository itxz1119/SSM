package com.bjpowernode.service.impl;

import com.bjpowernode.entity.Student;
import com.bjpowernode.mapper.StudentMapper;
import com.bjpowernode.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public StudentServiceImpl() {
        System.out.println("StudentServiceImpl无参构造========");
    }

    @Override
    public PageInfo<Student> pageList() {
        //开启分页 这个必须在第一排
        Page<Object> startPage = PageHelper.startPage(2, 3);
        //获取全部
        List<Student> studentList = studentMapper.list();
        //调用分页
        PageInfo<Student> studentPageInfo = new PageInfo<>(studentList);
        return studentPageInfo;
    }

    @Override
    public int updateStudent(int id) {

        return 0;
    }

    /*
    * 转账
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(int fromId, int toId, double money) {
        Student student = studentMapper.findById(fromId);
        student.setMoney(student.getMoney() - money);
        studentMapper.updateMoney(student);
        //int i = 10/0;
        Student toStudent = studentMapper.findById(toId);
        toStudent.setMoney(toStudent.getMoney() + money);
        studentMapper.updateMoney(toStudent);
    }
}
