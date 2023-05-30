package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

    private static SqlSessionFactory factory = null;

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            factory = null;
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        if (factory != null){
            sqlSession = factory.openSession(true);
        }
        return sqlSession;
    }
}
