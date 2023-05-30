import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.pojo.User;
import com.bjpowernode.util.MybatisUtil;
import com.bjpowernode.util.MybatisUtil2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserTest {

    @Test
    public void selectById() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        //User user = sqlSession.selectOne("com.bjpowernode.mapper.UserMapper.selectById", 3);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(3);
        System.out.println(user);
    }

    @Test
    public void selectByUser() throws IOException {
        UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);
        User user1 = new User();
        user1.setId(4);
        user1.setUsername("asd");
        User user = userMapper.selectByUser(user1);
        System.out.println(user);
    }

    @Test
    public void selectByUser2() throws IOException {
        UserMapper userMapper = MybatisUtil2.openSession(true).getMapper(UserMapper.class);
        User user1 = new User();
        user1.setId(4);
        user1.setUsername("asd");
        User user = userMapper.selectByUser(user1);
        System.out.println(user);
    }

    @Test
    public void updateUser() throws IOException {
        UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);
        User user1 = new User();
        user1.setId(4);
        user1.setUsername("asd");
        user1.setPwd("11111");
        user1.setBank_card("11111111111111");
        int i  = userMapper.updateUser(user1);
        System.out.println(i);
    }

    /*
    * 注解测试
    * */
    @Test
    public void selectUser(){
        UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);
        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }



}
