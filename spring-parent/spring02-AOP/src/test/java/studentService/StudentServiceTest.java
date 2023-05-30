package studentService;

import com.bjpowernode.aspect.serviceAspect.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void doSome() {
        StudentService studentService = context.getBean(StudentService.class);
        try {
            studentService.doSome("rose", 20);
        } catch (Exception e) {
            throw new RuntimeException("除数不能为零!");
        }
        //studentService.doSome("rose", 20);
        //studentService.doSome();
    }

    @Test
    public void doAny() {
        StudentService studentService = context.getBean(StudentService.class);
        int i = studentService.doAny(20);
        System.out.println(i);
    }

    @Test
    public void doOther() {
        StudentService studentService = context.getBean(StudentService.class);
        int i = studentService.doOther(18);
        System.out.println(i);
    }
}
