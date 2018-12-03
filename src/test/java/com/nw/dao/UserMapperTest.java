package com.nw.dao;

import com.nw.model.ULog;
import com.nw.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName UserMapperTest
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/11/28 12:39
 * @Version 1.0
 */
public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper uLogMapper = sqlSession.getMapper(UserMapper.class);
        User user = uLogMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }
}
