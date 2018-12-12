package com.nw.main;

import com.nw.dao.H000Mapper;
import com.nw.model.H000;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WriteDCSHistoryDataFromExcelToDBBatchInsert
 * @Description 从excel中读取文件批量录入数据到数据库
 *
 *              ***************************使用反射的方式************************
 *
 *                  1、文件名称与位置：d:/mw.xlsx
 *                  2、文件中的sheet名称也是mw
 *              main方法中含有删除所有数据的代码，注意屏蔽
 *
 *
 *
 *
 * @Author jiangyong xia
 * @Date 18/12/12 12:08
 * @Version 1.0
 */
public class WriteDCSHistoryDataFromExcelToDBBatchInsertAndReflect {

    /**
     * @Description
     * @Author jiangyong xia
     * @Date 15:45 18/12/12
     * @Param
     * @return
     **/
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        WriteDCSHistoryDataFromExcelToDBBatchInsertAndReflect w = new WriteDCSHistoryDataFromExcelToDBBatchInsertAndReflect();
        w.init();
        //删除h000所有记录
//        w.deleteAll("H199","deleteAll");
        //读取excel数据，录入到数据库中
//        w.readExcelDataToDB("mw","h000");
        w.readExcelDataToDB("mw","H199","insertBatch");
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

    /**
     *
     * @param classSimpleName   实体类名称
     * @param methodName        方法名称
     * @throws IOException                  异常
     * @throws ClassNotFoundException       异常
     * @throws NoSuchMethodException        异常
     * @throws InvocationTargetException    异常
     * @throws IllegalAccessException       异常
     */
    public void deleteAll(String classSimpleName,String methodName) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SqlSession session = sqlSessionFactory.openSession(true);
        Class c = Class.forName("com.nw.dao."+classSimpleName+"Mapper");
        Object o = session.getMapper(c);
        Method m = c.getMethod(methodName);
        m.invoke(o);

    }
    /**
     * @Description
     * @Author jiangyong xia
     * @Date 15:43 18/12/12
     * @Param sheetOrFileName
     * @Param   tableName
     * @return
     **/
    private void readExcelDataToDB(String sheetOrFileName,String classSimpleName,String insertBatchMethodName) {
        try {
            String name = sheetOrFileName;
            String fileName = "d:/" + name + ".xlsx";
            File file = new File(fileName);
            if (null != file) {
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                Sheet sheet = workbook.getSheet(name);
                int rowNumBegin = 5;
                Class pojoClass = Class.forName("com.nw.model."+classSimpleName);
                Method setVMethod = pojoClass.getMethod("setV",Float.class);
                Method setVTimeMethod = pojoClass.getMethod("setvTime",java.util.Date.class);
                List<Object> hList = new ArrayList<>();
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                Class mapperClass = Class.forName("com.nw.dao."+classSimpleName + "Mapper");
                Method insertBatchMethod = mapperClass.getMethod(insertBatchMethodName,List.class);
                Object mapper = sqlSession.getMapper(mapperClass);

                while (true) {
                    Row row = sheet.getRow(rowNumBegin++);
                    if (null != row) {
                        Cell cell = row.getCell(0);
                        if (null != cell) {
                            Date date = cell.getDateCellValue();
                            Cell cell1 = row.getCell(1);
                            Double value = cell1.getNumericCellValue();
                                Object tempObj = pojoClass.newInstance();
                                setVMethod.invoke(tempObj,value.floatValue()*100);
                                setVTimeMethod.invoke(tempObj,new Timestamp(date.getTime()));
                                hList.add(tempObj);
                                if(hList.size() >100) {
                                    insertBatchMethod.invoke(mapper,hList);
//                                    Thread.sleep(10);
                                    System.out.println("保存");
                                    hList.clear();
                                }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if(null != hList &&hList.size() > 0){
                    insertBatchMethod.invoke(mapper,hList);
                }
            }
            System.out.println(sheetOrFileName +",文件读取，并录入结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
