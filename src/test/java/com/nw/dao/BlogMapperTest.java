package com.nw.dao;

import com.nw.model.Blog;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName BlogMapperTest
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/11/28 9:44
 * @Version 1.0
 */
public class BlogMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException{
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test() throws IOException{
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<Blog> blogList = sqlSession.selectList("com.nw.dao.BlogMapper.selectByPrimaryKey",1);
        blogList.forEach(System.out::println);
        sqlSession.close();

    }
    @Test
    public void test1(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<Blog> blogList = sqlSession.selectList("com.nw.dao.BlogMapper.findAll");
        blogList.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void test2(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectByPrimaryKey(1);
        System.out.println(blog);
    }
    @Test
    public void test3(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.findById(1);
        System.out.println(blog);
    }
}
