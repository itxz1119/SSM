import com.bjpowernode.mapper.StudentMapper;
import com.bjpowernode.pojo.Student;
import com.bjpowernode.util.MybatisUtil2;
import com.bjpowernode.vo.StudentVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentTest {

    private StudentMapper studentMapper = MybatisUtil2.openSession(true).getMapper(StudentMapper.class);

    /*
     * 动态sql,foreach批量删除,查询
     * */
    @Test
    public void deleteStudents() {
        ArrayList<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        ids.add("4");
        try {
            int i = studentMapper.deleteStudents(ids);
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("list集合为空");
        }
    }

    @Test
    public void deleteStudentByArray() {
        int[] ids = {};
        try {
            int i = studentMapper.deleteStudentByArray(ids);
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("数组为空");
        }
    }
//简单类型
    @Test
    public void findStuByIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        List<Student> studentList = studentMapper.findStuByIds(ids);
        studentList.forEach(System.out::println);
    }
//    对象类型
    @Test
    public void findStuByStus() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1));
        students.add(new Student(2));
        students.add(new Student(3));
        students.add(new Student(4));
        List<Student> studentList = studentMapper.findStuByStus(students);
        studentList.forEach(System.out::println);
    }

    //模糊查询
    @Test
    public void findStuByName() {
        List<Student> studentList = studentMapper.findStuByName("ro");
        studentList.forEach(System.out::println);
    }

    /*
     * 多条件组合查询
     * */
    @Test
    public void findStuPage() {
        int currentPage = 1;
        int pageSize = 3;
        int start = (currentPage - 1) * pageSize;
        StudentVo studentVo = new StudentVo();
        //studentVo.setName("ro");
        //studentVo.setMinAge(30);
        studentVo.setMaxAge(26);
//        studentVo.setStart(start);
//        studentVo.setPageSize(pageSize);
//        分页插件,调用startPage()方法,紧跟在这个之后的第一个方法会进行分页
        Page<Object> startPage = PageHelper.startPage(1, 2);
        List<Student> studentList = studentMapper.findStuPage(studentVo);
        studentList.forEach(System.out::println);
    }

    /*
    * 一对一
    * */
    @Test
    public void findStuClassRoom(){
        Student student = studentMapper.findStuClassRoom(5);
        System.out.println(student);
    }

}
