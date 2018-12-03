package com.nw.main;

import com.nw.dao.CoalPipingHistoryMillAMapper;
import com.nw.dao.CoalPipingHistoryMillBMapper;
import com.nw.dao.CoalPipingHistoryMillCMapper;
import com.nw.dao.CoalPipingHistoryMillDMapper;
import com.nw.excel.WriteDataToExcel;
import com.nw.model.*;
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
public class WriteDataMain {

    private SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        WriteDataMain writeDataMain = new WriteDataMain();
        LocalDateTime localDateTimeBegin = LocalDateTime.of(2018, 10, 26, 0, 0);
        LocalDateTime localDateTimeEnd = LocalDateTime.of(2018, 11, 20, 0, 00);
        try {
            Date begin = DateUtil.getDateFromLocalDateTime(localDateTimeBegin);
            Date end = DateUtil.getDateFromLocalDateTime(localDateTimeEnd);
            writeDataMain.writeDataMainWithA(begin, end);
            writeDataMain.writeDataMainWithB(begin, end);
            writeDataMain.writeDataMainWithC(begin, end);
            writeDataMain.writeDataMainWithD(begin, end);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实例化 sqlSessionFactory
     * @throws IOException  异常
     */
    public void init() throws IOException {
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 查询并写入10磨的历史数据到excel表格
     * 表格的地址为： d:\10磨11月15日-11月16日.xlsx
     * 每天生成一个excel文件
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @throws IOException  异常
     */
    public void writeDataMainWithA(Date beginDate, Date endDate) throws IOException {
        String millName = "10磨";
        writeData(millName,beginDate,endDate);
    }
    /**
     * 查询并写入20磨的历史数据到excel表格
     * 表格的地址为： d:\20磨11月15日-11月16日.xlsx
     * 每天生成一个excel文件
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @throws IOException  异常
     */
    public void writeDataMainWithB(Date beginDate, Date endDate) throws IOException {
        String millName = "20磨";
        writeData(millName,beginDate,endDate);

    }
    /**
     * 查询并写入30磨的历史数据到excel表格
     * 表格的地址为： d:\30磨11月15日-11月16日.xlsx
     * 每天生成一个excel文件
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @throws IOException  异常
     */
    public void writeDataMainWithC(Date beginDate, Date endDate) throws IOException {
        String millName = "30磨";
        writeData(millName,beginDate,endDate);
    }

    /**
     * 查询并写入40磨的历史数据到excel表格
     * 表格的地址为： d:\40磨11月15日-11月16日.xlsx
     * 每天生成一个excel文件
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @throws IOException  异常
     */
    public void writeDataMainWithD(Date beginDate, Date endDate) throws IOException {
        String millName = "40磨";
        writeData(millName,beginDate,endDate);
    }

    /**
     * 查询指定磨煤机，在查询时间范围内的历史数据，并存入excel表格中
     * 表格的地址为： d:\10磨11月15日-11月16日.xlsx
     * 每天生成一个excel文件
     * @param millName      磨煤机名称
     * @param beginDate     开始时间
     * @param endDate       结束时间
     * @throws IOException  异常
     */
    public void writeData(String millName,Date beginDate,Date endDate) throws IOException {
        LocalDateTime localDateTimeBegin = DateUtil.getLocalDateTimeFromDate(beginDate);
        LocalDateTime localDateTimeEnd = DateUtil.getLocalDateTimeFromDate(endDate);
        Period period = Period.between(localDateTimeBegin.toLocalDate(), localDateTimeEnd.toLocalDate());
        int totalDays = period.getDays();
        for (int i = 0; i < totalDays; i++) {
            List<? extends CoalPipingHistory> coalPipingHistories = findCoalPipingHistory(millName, localDateTimeBegin, i);
            String fileName = getFileNameAndGeneratorFile(millName, localDateTimeBegin,i);
            WriteDataToExcel writeDataToExcel = new WriteDataToExcel();
            writeDataToExcel.insertCoalPipingHistoryMillDataListToExcel(coalPipingHistories, fileName);
        }
    }


    /**
     * 查询指定磨煤机，在指定开始时间，指定第几天的历史粉管数据
     * @param millName              磨煤机名称
     * @param localDateTimeBegin    开始查询时间
     * @param days                  第几天
     * @return                      List<? extends CoalPipingHistory>
     * @throws IOException          异常
     */
    private List<? extends CoalPipingHistory> findCoalPipingHistory(String millName, LocalDateTime localDateTimeBegin, int days) throws IOException {
        LocalDateTime localDateTimeBeginTemp = localDateTimeBegin.plusDays(days);
        LocalDateTime localDateTimeEndTemp = localDateTimeBegin.plusDays(days + 1);
        Date begin = DateUtil.getDateFromLocalDateTime(localDateTimeBeginTemp);
        Date end = DateUtil.getDateFromLocalDateTime(localDateTimeEndTemp);
        return findCoalPipingHistory(millName,begin,end);
    }

    /**
     * 查询指定磨煤机，在指定时间内的历史粉管数据
     * @param millName  磨煤机名称
     * @param begin     开始时间
     * @param end       结束时间
     * @return          List<? extends CoalPipingHistory>
     * @throws IOException  异常
     */
    private List<? extends CoalPipingHistory> findCoalPipingHistory(String millName, Date begin, Date end) throws IOException {
        if (null == sqlSessionFactory) {
            init();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<? extends CoalPipingHistory> coalPipingHistories = null;
        if (millName.equals("10磨")) {
            CoalPipingHistoryMillAMapper coalPipingHistoryMillAMapper = sqlSession.getMapper(CoalPipingHistoryMillAMapper.class);
            coalPipingHistories = coalPipingHistoryMillAMapper.findListBetweenTime(begin, end);
        } else if (millName.equals("20磨")) {
            CoalPipingHistoryMillBMapper coalPipingHistoryMillBMapper = sqlSession.getMapper(CoalPipingHistoryMillBMapper.class);
            coalPipingHistories = coalPipingHistoryMillBMapper.findListBetweenTime(begin, end);
        } else if (millName.equals("30磨")) {
            CoalPipingHistoryMillCMapper coalPipingHistoryMillCMapper = sqlSession.getMapper(CoalPipingHistoryMillCMapper.class);
            coalPipingHistories = coalPipingHistoryMillCMapper.findListBetweenTime(begin, end);
        } else if (millName.equals("40磨")) {
            CoalPipingHistoryMillDMapper coalPipingHistoryMillDMapper = sqlSession.getMapper(CoalPipingHistoryMillDMapper.class);
            coalPipingHistories = coalPipingHistoryMillDMapper.findListBetweenTime(begin, end);
        }
        return coalPipingHistories;

    }

    /**
     * 生成文件名称
     * FX: 10磨11月16日-11月28日.xlsx
     *  如果文件不存在，则生成文件
      * @param millName         磨煤机名称
     * @param localDateTimeBegin    开始查询时间
     * @param days                  第几天
     * @return                  文件名称
     * @throws IOException      异常
     */
    public String getFileNameAndGeneratorFile(String millName, LocalDateTime localDateTimeBegin, int days) throws IOException {
        LocalDateTime localDateTimeBeginTemp = localDateTimeBegin.plusDays(days);
        LocalDateTime localDateTimeEndTemp = localDateTimeBegin.plusDays(days + 1);
        Date begin = DateUtil.getDateFromLocalDateTime(localDateTimeBeginTemp);
        Date end = DateUtil.getDateFromLocalDateTime(localDateTimeEndTemp);
        return getFileNameAndGeneratorFile(millName,begin,end);
    }

    /**
     *  生成文件名称
     *  FX: 10磨11月16日-11月28日.xlsx
     *  如果文件不存在，则生成文件
     * @param millName  磨煤机名称
     * @param begin 开始时间
     * @param end   结束时间
     * @return  String 文件名称
     * @throws IOException  异常
     */
    public String getFileNameAndGeneratorFile(String millName, Date begin, Date end) throws IOException {
        LocalDateTime localDateTimeBegin = DateUtil.getLocalDateTimeFromDate(begin);
        LocalDateTime localDateTimeEnd = DateUtil.getLocalDateTimeFromDate(end);
        String fileName = "d:/" + millName + localDateTimeBegin.getMonthValue() + "月" + localDateTimeBegin.getDayOfMonth() + "日";
        fileName += "-" + localDateTimeEnd.getMonthValue() + "月" + localDateTimeEnd.getDayOfMonth() + "日" + ".xlsx";

        File file = new File(fileName);
        file.createNewFile();
        return fileName;
    }


}
