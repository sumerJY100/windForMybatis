package com.nw.main;

import com.nw.dao.*;
import com.nw.model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WriteDCSHistoryDataFromExcelToDB
 * @Description
 * 将excel中的文件写入到数据库中
 *  1、文件位置：d盘
 *  2、文件名称：10磨磨煤量.xlsx
 *  3、文件中sheet的名称与文件名称一致，例如：10磨磨煤量，则sheet的名称为10磨磨煤量
 *  4、main方法中含有删除所有数据的方法，使用时注意
 * @Author jiangyong xia
 * @Date 18/12/12 9:54
 * @Version 1.0
 */
public class WriteDCSHistoryDataFromExcelToDB {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 实例化 sqlSessionFactory
     *
     * @throws IOException 异常
     */
    public void init() throws IOException {
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    public static void main(String[] args) throws IOException {
        WriteDCSHistoryDataFromExcelToDB w = new WriteDCSHistoryDataFromExcelToDB();
        w.init();
        //删除4台磨的磨煤量DCS数据
//        w.deleteCoalMillData();
        //从excle文件中读取磨煤量数据，写入数据库
        w.readDataToWriteDataToDB();
    }

    /**
     * @return
     * @Description 读取excel中的数据，写入到数据库中
     * @Author jiangyong xia
     * @Date 10:38 18/12/12
     * @Param
     **/
    private void readDataToWriteDataToDB() {
        try {
            new Thread(() -> readMillDataToDB("10磨磨煤量", "074")).start();
            new Thread(() -> readMillDataToDB("20磨磨煤量", "075")).start();
            new Thread(() -> readMillDataToDB("30磨磨煤量", "076")).start();
            new Thread(() -> readMillDataToDB("40磨磨煤量", "077")).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @Description 读取10磨的历史数据，写入到h074表中
     * @Author jiangyong xia
     * @Date 10:39 18/12/12
     * @Param
     **/
    private void readMillDataToDB(String sheetOrFileName, String tableName) {
//        String name = "40磨磨煤量";
//        String hName = "077";
        try {
            String name = sheetOrFileName;
            String hName = tableName;
            String fileName = "d:/" + name + ".xlsx";
            File file = new File(fileName);
            if (null != file) {
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                Sheet sheet = workbook.getSheet(name);
                int rowNumBegin = 5;

                List<H074> h074List = new ArrayList<>();
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                H074Mapper h074Mapper = sqlSession.getMapper(H074Mapper.class);
                H075Mapper h075Mapper = sqlSession.getMapper(H075Mapper.class);
                H076Mapper h076Mapper = sqlSession.getMapper(H076Mapper.class);
                H077Mapper h077Mapper = sqlSession.getMapper(H077Mapper.class);
                while (true) {
                    Row row = sheet.getRow(rowNumBegin++);
                    if (null != row) {
                        Cell cell = row.getCell(0);
                        if (null != cell) {
                            Date date = cell.getDateCellValue();
                            Cell cell1 = row.getCell(1);
                            Double value = cell1.getNumericCellValue();
                            if (hName.equals("074")) {
                                H074 h074 = new H074();
                                h074.setvTime(new Timestamp(date.getTime()));
                                h074.setV(value.floatValue() * 100);
                                h074List.add(h074);
                                h074Mapper.insert(h074);
                            } else if (hName.equals("075")) {
                                H075 h075 = new H075();
                                h075.setvTime(new Timestamp(date.getTime()));
                                h075.setV(value.floatValue() * 100);
                                h075Mapper.insert(h075);
                            } else if (hName.equals("076")) {
                                H076 h076 = new H076();
                                h076.setvTime(new Timestamp(date.getTime()));
                                h076.setV(value.floatValue() * 100);
                                h076Mapper.insert(h076);
                            } else if (hName.equals("077")) {
                                H077 h077 = new H077();
                                h077.setvTime(new Timestamp(date.getTime()));
                                h077.setV(value.floatValue() * 100);
                                h077Mapper.insert(h077);
                            }
                            Thread.sleep(10);
//                        System.out.println("保存");
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }


            }
            System.out.println(sheetOrFileName +",文件读取，并录入结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @Description 删除4台磨煤机的磨煤量DCS数据
     * 10磨对应h074
     * 20磨对应h075
     * 30磨对应h076
     * 40磨对应h077
     * @Author jiangyong xia
     * @Date 10:29 18/12/12
     * @Param
     **/
    public void deleteCoalMillData() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<? extends CoalPipingHistory> coalPipingHistories = null;
//        if (millName.equals("10磨")) {
        CoalPipingHistoryMillAMapper coalPipingHistoryMillAMapper = sqlSession.getMapper(CoalPipingHistoryMillAMapper.class);
        H074Mapper h074Mapper = sqlSession.getMapper(H074Mapper.class);
        h074Mapper.deleteAll();
        H075Mapper h075Mapper = sqlSession.getMapper(H075Mapper.class);
        h075Mapper.deleteAll();
        H076Mapper h076Mapper = sqlSession.getMapper(H076Mapper.class);
        h076Mapper.deleteAll();
        H077Mapper h077Mapper = sqlSession.getMapper(H077Mapper.class);
        h077Mapper.deleteAll();
    }


}
