package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Student;
import com.bjpowernode.vo.StudentVo;

import java.util.List;

public interface StudentMapper {

    /*
     * 动态sql,foreach批量删除,查询
     * */
    int deleteStudents(List<String> ids);
    int deleteStudentByArray(int[] ids);
    List<Student> findStuByIds(List<Integer> ids);
    List<Student> findStuByStus(List<Student> students);
    /*
    * 模糊查询
    * */
    List<Student> findStuByName(String name);

    /*
    * 多条件组合查询
    * */
    List<Student> findStuPage(StudentVo studentVo);

    /*
    * 一对一
    * */
    Student findStuClassRoom(int id);

}
