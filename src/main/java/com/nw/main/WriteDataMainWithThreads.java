package com.nw.main;

import com.nw.dao.CoalPipingHistoryMillCMapper;
import com.nw.excel.WriteDataToExcel;
import com.nw.excel.WriteDataTowExcelBigData;
import com.nw.model.CoalPipingHistoryMillC;
import com.nw.util.DateUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WriteDataMain
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/11/28 14:37
 * @Version 1.0
 */
public class WriteDataMainWithThreads {

    private SqlSessionFactory sqlSessionFactory;

    public void init() throws IOException {
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void main(String[] args) {
        WriteDataMainWithThreads writeDataMain = new WriteDataMainWithThreads();
        LocalDateTime localDateTimeBegin = LocalDateTime.of(2018,10,26,0,0);
        LocalDateTime localDateTimeEnd = LocalDateTime.of(2018,11,20,0,00);
        try {
            writeDataMain.writeDataMain(DateUtil.getDateFromLocalDateTime(localDateTimeBegin),DateUtil
                    .getDateFromLocalDateTime(localDateTimeEnd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void writeDataMain(Date beginDate, Date endDate) throws IOException {
        init();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        CoalPipingHistoryMillCMapper coalPipingHistoryMillBMapper = sqlSession.getMapper(CoalPipingHistoryMillCMapper
                .class);

        LocalDateTime localDateTimeBegin = DateUtil.getLocalDateTimeFromDate(beginDate);
        LocalDateTime localDateTimeEnd = DateUtil.getLocalDateTimeFromDate(endDate);
        Period period = Period.between(localDateTimeBegin.toLocalDate(),localDateTimeEnd.toLocalDate());
        int totalDays = period.getDays();
        for(int i=0;i<totalDays;i++){

            int count = i;

                LocalDateTime localDateTimeBeginTemp = localDateTimeBegin.plusDays(count);
                LocalDateTime localDateTimeEndTemp = localDateTimeBegin.plusDays(count+1);
                Date begin = DateUtil.getDateFromLocalDateTime(localDateTimeBeginTemp);
                Date end = DateUtil.getDateFromLocalDateTime(localDateTimeEndTemp);
//                SqlSession sqlSession = sqlSessionFactory.openSession(true);
//                CoalPipingHistoryMillCMapper coalPipingHistoryMillBMapper = sqlSession.getMapper(CoalPipingHistoryMillCMapper
//                        .class);
                System.out.println("coalPipingHistoryMillBMapper:" + coalPipingHistoryMillBMapper);
                List<CoalPipingHistoryMillC> coalPipingHistoryMillBList = coalPipingHistoryMillBMapper
                        .findListBetweenTime
                        (begin,end);
//                sqlSession.close();
            new Thread(()->{
                String fileName = null;
                try {
                    fileName = getFileNameAndGeneratorFile(begin,end);
                    System.out.println("写入新数据" + fileName+"," + LocalDateTime.now().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //大量数据写入使用此类
                WriteDataTowExcelBigData writeDataToExcel = new WriteDataTowExcelBigData();
                try {
                    writeDataToExcel.insertCoalPipingHistoryMillDataListToExcel(coalPipingHistoryMillBList,fileName);
                    System.out.println("结束"+ fileName + ","+ LocalDateTime.now().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }


    public String getFileNameAndGeneratorFile(Date begin,Date end) throws IOException {
        LocalDateTime localDateTimeBegin = DateUtil.getLocalDateTimeFromDate(begin);
        LocalDateTime localDateTimeEnd = DateUtil.getLocalDateTimeFromDate(end);
        String fileName ="d:/20磨" + localDateTimeBegin.getMonthValue()+"月" + localDateTimeBegin.getDayOfMonth() + "日";
        fileName += "-"+ localDateTimeEnd.getMonthValue() + "月" + localDateTimeEnd.getDayOfMonth() + "日" + ".xlsx";

        File file = new File(fileName);
        file.createNewFile();
        return fileName;
    }


}
