package com.nw.dao;

import com.nw.model.CoalPipingHistoryMillA;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @ClassName CoalPipingHistoryMillAMapperTest
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/11/28 14:04
 * @Version 1.0
 */
public class CoalPipingHistoryMillAMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void findListBetweenTimeTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        CoalPipingHistoryMillAMapper coalPipingHistoryMillAMapper = sqlSession.getMapper(CoalPipingHistoryMillAMapper.class);
        Calendar calendarBegin = new GregorianCalendar();
        calendarBegin.set(Calendar.HOUR_OF_DAY,10);
        calendarBegin.set(2018,10,26);
        Date begin = calendarBegin.getTime();
        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.set(Calendar.HOUR_OF_DAY,12);
        calendarEnd.set(2018,10,26);
        Date end = calendarEnd.getTime();
        coalPipingHistoryMillAMapper.selectByPrimaryKey(1L);

        List<CoalPipingHistoryMillA> coalPipingHistoryMillAList = coalPipingHistoryMillAMapper.findListBetweenTime
                (begin,end);
        System.out.println(coalPipingHistoryMillAList.size());
        for(CoalPipingHistoryMillA coalPipingHistoryMillA:coalPipingHistoryMillAList){
            System.out.println(coalPipingHistoryMillA.gethTime() + "," + coalPipingHistoryMillA.getId()+ "," +
                    coalPipingHistoryMillA.gethPipeaDencity() + "," + coalPipingHistoryMillA.gethPipeaX());
        }
    }
}
