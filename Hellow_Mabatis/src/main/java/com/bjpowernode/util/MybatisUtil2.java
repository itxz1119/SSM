package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtil2 {

    private static SqlSessionFactory factory = null;

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (Exception e) {
            factory = null;
            e.printStackTrace();
        }
    }

    public static SqlSession openSession(boolean autoCommit) {
        SqlSession sqlSession = null;
        if (factory != null) {
            sqlSession = factory.openSession(autoCommit);
        }
        return sqlSession;
    }

    public static void close(SqlSession sqlSession){
        if (sqlSession != null){
            sqlSession.close();
        }
    }
}
