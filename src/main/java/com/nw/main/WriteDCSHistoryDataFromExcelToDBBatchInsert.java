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
 * @ClassName WriteDCSHistoryDataFromExcelToDBBatchInsert
 * @Description     从excel文件中读取发电功率的数据，到h000数据库中
 *              从excel中读取文件批量录入数据到数据库
 *                  1、文件名称与位置：d:/nw.xlsx
 *              main方法中含有删除所有数据的代码，注意屏蔽
 *
 *
 * @Author jiangyong xia
 * @Date 18/12/12 12:08
 * @Version 1.0
 */
public class WriteDCSHistoryDataFromExcelToDBBatchInsert {

    public static void main(String[] args) throws IOException {
        WriteDCSHistoryDataFromExcelToDBBatchInsert w = new WriteDCSHistoryDataFromExcelToDBBatchInsert();
        //批量插入 例子
//        w.insertBath();
        //删除h000所有记录
//        w.deleteAll();
        //读取excel数据，录入到数据库中
        w.readExcelDataToDB("mw","h000");
    }


    private SqlSessionFactory sqlSessionFactory;

    /**
     * 实例化 sqlSessionFactory
     * @throws IOException 异常
     */
    public void init() throws IOException {
        InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    public void deleteAll() throws IOException {
        init();
        SqlSession session = sqlSessionFactory.openSession(true);
        H000Mapper h000Mapper = session.getMapper(H000Mapper.class);
        h000Mapper.deleteAll();
    }
    private void readExcelDataToDB(String sheetOrFileName, String tableName) {
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

                List<H000> h000List = new ArrayList<>();
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                H000Mapper h000Mapper = sqlSession.getMapper(H000Mapper.class);
                while (true) {
                    Row row = sheet.getRow(rowNumBegin++);
                    if (null != row) {
                        Cell cell = row.getCell(0);
                        if (null != cell) {
                            Date date = cell.getDateCellValue();
                            Cell cell1 = row.getCell(1);
                            Double value = cell1.getNumericCellValue();
                                H000 h000 = new H000();
                                h000.setvTime(new Timestamp(date.getTime()));
                                h000.setV(value.floatValue() * 100);
                                h000List.add(h000);
                                if(h000List.size() >100) {
                                    h000Mapper.insertBatch(h000List);
                                    Thread.sleep(10);
                                    System.out.println("保存");
                                    h000List.clear();
                                }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                if(null != h000List &&h000List.size() > 0){
                    h000Mapper.insertBatch(h000List);
                }
            }

            System.out.println(sheetOrFileName +",文件读取，并录入结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description 数据批量插入模拟
     * @Author jiangyong xia
     * @Date 12:48 18/12/12
     * @Param
     * @return
     **/
    public void insertBath() throws IOException {
        init();
        SqlSession session = sqlSessionFactory.openSession(true);
        H000Mapper h000Mapper = session.getMapper(H000Mapper.class);
        List<H000> h000List = new ArrayList<>();
        for(int i=0;i<10;i++){
            H000 h000 = new H000();
            h000.setV((float) (Math.random()*100));
            h000.setvTime(new Date());
            h000List.add(h000);
        }
        h000Mapper.insertBatch(h000List);
    }


}
