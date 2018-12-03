package com.nw.excel;

import com.nw.model.CoalPipingHistory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName WriteDataTowExcelBigData
 * @Description 此类中使用SXXFWorkbook，可是适用于批量写入数据。比XXFWorkbook的速度快50倍
 * @Author jiangyong xia
 * @Date 18/11/28 17:35
 * @Version 1.0
 */
public class WriteDataTowExcelBigData {
    /**
     * 将coalPipingHistoryMillAList的数据写入到指定文件fileName中
     * @param coalPipingHistories    List<? extends CoalPipingHistory>
     * @param fileName  文件名
     * @throws IOException  异常
     */
    public void insertCoalPipingHistoryMillDataListToExcel(List<? extends CoalPipingHistory> coalPipingHistories, String fileName) throws IOException {
        SXSSFWorkbook workbook = generateCoalPipingHistoryMillDataListToExcel(coalPipingHistories);
        //写入数据
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        workbook.close();
    }
    /**
     * 生成一个XSSFWorkbook对象，
     * 并将coalPipingHistoryMillAList中所有的4个管道的x、y、v、density与velocity数据写入到excel表格中
     * @param coalPipingHistoryMillAList  List<? extends CoalPipingHistory>
     * @return XSSFWorkbook
     */
    public SXSSFWorkbook generateCoalPipingHistoryMillDataListToExcel(List<? extends CoalPipingHistory>
                                                                              coalPipingHistoryMillAList){
        //创建一个工作簿
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //创建一个电子表格
        SXSSFSheet sheet = workbook.createSheet("mySheet");
//        Row firstRow = sheet.createRow(0);
        for(int i=0;i<coalPipingHistoryMillAList.size();i++){
            int rowNum = i+1;
            SXSSFRow row;
            //1.创建一个行对象
            row = sheet.createRow(rowNum);
            //2.创建单元格，并给单元格赋值
//            System.out.println(coalPipingHistoryMillAList.get(i).gethTime() + "," + coalPipingHistoryMillAList.get(i)
//                    .gethPipeaDencity() + "," + coalPipingHistoryMillAList.get(i).getId());
            WriteDataToExcel.insertCell(rowNum,row,coalPipingHistoryMillAList.get(i));
        }
        return workbook;
    }

}
