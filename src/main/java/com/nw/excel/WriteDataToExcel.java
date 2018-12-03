package com.nw.excel;

import com.nw.model.CoalPipingHistory;
import com.nw.model.CoalPipingHistoryMillA;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName WriteDataToExcel
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/11/28 13:47
 * @Version 1.0
 */
public class WriteDataToExcel {

    /**
     * 写数据到d:/demo.xlsx文件中 ，此方法为写数据的demo
     * @throws IOException 异常
     */
    public void writeDateToExcel() throws IOException {
        String path = "d:/demo.xlsx";
        File file = new File(path);
        file.createNewFile();
        FileInputStream fileInputStream = new FileInputStream(path);
        //创建一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个电子表格
        XSSFSheet sheet = workbook.createSheet("mySheet");
        //行对象
        XSSFRow row;
        for (int r = 0; r < 3; r++) {
            //1.创建一个行对象
            row = sheet.createRow(r);
            for (int c = 0; c < 3; c++) {
                //2.创建一个单元格
                Cell cell = row.createCell(c);
                cell.setCellValue(r + c);
            }
        }
        FileOutputStream fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();

    }

    /**
     * 将coalPipingHistoryMillAList的数据写入到指定文件fileName中
     * @param coalPipingHistories    List<? extends CoalPipingHistory>
     * @param fileName  文件名
     * @throws IOException  异常
     */
    public void insertCoalPipingHistoryMillDataListToExcel(List<? extends CoalPipingHistory> coalPipingHistories, String fileName) throws IOException {
        XSSFWorkbook workbook = generateCoalPipingHistoryMillDataListToExcel(coalPipingHistories);
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
    public XSSFWorkbook generateCoalPipingHistoryMillDataListToExcel(List<? extends CoalPipingHistory> coalPipingHistoryMillAList){
        //创建一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个电子表格
        XSSFSheet sheet = workbook.createSheet("mySheet");
        for(int i=0;i<coalPipingHistoryMillAList.size();i++){
            int rowNum = i+1;
            XSSFRow row;
            //1.创建一个行对象
            row = sheet.createRow(rowNum);
            //2.创建单元格，并给单元格赋值
//            System.out.println(coalPipingHistoryMillAList.get(i).gethTime() + "," + coalPipingHistoryMillAList.get(i)
//                    .gethPipeaDencity() + "," + coalPipingHistoryMillAList.get(i).getId());
            insertCell(rowNum,row,coalPipingHistoryMillAList.get(i));
        }
        return workbook;
    }


    /**
     * 生成一个XSSFWorkbook对象，
     * 在指定行中添加的4个管道的x、y、v、density与velocity数据写入到excel表格中
     * @param rowNum    指定行
     * @param pipingHistory CoalPipingHistory
     * @return
     */
    public XSSFWorkbook generatorWorkBookWithCoalPipingHistoryMillA(int rowNum, CoalPipingHistory pipingHistory){
        //创建一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个电子表格
        XSSFSheet sheet = workbook.createSheet("mySheet");
        //行对象
        XSSFRow row;
        //1.创建一个行对象
        row = sheet.createRow(rowNum);
        //2.创建单元格，并给单元格赋值
        insertCell(rowNum,row,pipingHistory);
        return workbook;
    }

    /**
     * 在指定excle文件中，指定行，插入一条coalPipingHistoryMillA的数据
     * @param rowNum    指定行
     * @param coalPipingHistoryMillA    指定对象
     * @param FileName  指定文件
     * @throws IOException  异常
     */
    public void insertCoalPipingHistoryMillDataToExcel(int rowNum, CoalPipingHistoryMillA coalPipingHistoryMillA, String FileName) throws
            IOException {
        FileInputStream fileInputStream = new FileInputStream(FileName);
        //创建一个工作簿
        XSSFWorkbook workbook = generatorWorkBookWithCoalPipingHistoryMillA(rowNum,coalPipingHistoryMillA);

        //写入数据
        FileOutputStream fos = new FileOutputStream(FileName);
        workbook.write(fos);
        workbook.close();
    }

    /**
     * 在指定行中添加CoalPipingHistory的数据，数据包含：
     *      序号  日期  时间  1-X,1-Y,1-V,1-XYAVG，1-v，2……
     * @param rowNum    序号
     * @param row   XSSFRow
     * @param pipingHistory CoalPipingHistory
     */
    public static void insertCell(int rowNum, Row row, CoalPipingHistory pipingHistory){
        int columnIndex = 1;
        Cell cellIndex = row.createCell(columnIndex++);
        cellIndex.setCellValue(rowNum);

        LocalDateTime localDateTime = com.nw.util.DateUtil.getLocalDateTimeFromDate(pipingHistory.gethTime());
        Cell cellHDate = row.createCell(columnIndex++);
        cellHDate.setCellValue(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Cell cellHTime = row.createCell(columnIndex++);
        cellHTime.setCellValue(localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        Cell cellAX = row.createCell(columnIndex++);
        cellAX.setCellValue(Optional.ofNullable(pipingHistory.gethPipeaX()).orElse(0f));
        Cell cellAY = row.createCell(columnIndex++);
        cellAY.setCellValue(Optional.ofNullable(pipingHistory.gethPipeaY()).orElse(0f));
        Cell cellAV = row.createCell(columnIndex++);
        cellAV.setCellValue(Optional.ofNullable(pipingHistory.gethPipeaV()).orElse(0f));

        Cell cellADensity = row.createCell(columnIndex++);
        cellADensity.setCellValue(Optional.ofNullable(pipingHistory.gethPipeaDencity()).orElse(0f));
        Cell cellAVelocity = row.createCell(columnIndex++);
        cellAVelocity.setCellValue(Optional.ofNullable(pipingHistory.gethPipeaVelocity()).orElse(0f));




        Cell cellBX = row.createCell(columnIndex++);
        cellBX.setCellValue(Optional.ofNullable(pipingHistory.gethPipebX()).orElse(0f));
        Cell cellBY = row.createCell(columnIndex++);
        cellBY.setCellValue(Optional.ofNullable(pipingHistory.gethPipebY()).orElse(0f));
        Cell cellBV = row.createCell(columnIndex++);
        cellBV.setCellValue(Optional.ofNullable(pipingHistory.gethPipebV()).orElse(0f));


        Cell cellBDensity = row.createCell(columnIndex++);
        cellBDensity.setCellValue(Optional.ofNullable(pipingHistory.gethPipebDencity()).orElse(0f));
        Cell cellBVelocity = row.createCell(columnIndex++);
        cellBVelocity.setCellValue(Optional.ofNullable(pipingHistory.gethPipebVelocity()).orElse(0f));



        Cell cellCX = row.createCell(columnIndex++);
        cellCX.setCellValue(Optional.ofNullable(pipingHistory.gethPipecX()).orElse(0f));
        Cell cellCY = row.createCell(columnIndex++);
        cellCY.setCellValue(Optional.ofNullable(pipingHistory.gethPipecY()).orElse(0f));
        Cell cellCV = row.createCell(columnIndex++);
        cellCV.setCellValue(Optional.ofNullable(pipingHistory.gethPipecV()).orElse(0f));



        Cell cellCDensity = row.createCell(columnIndex++);
        cellCDensity.setCellValue(Optional.ofNullable(pipingHistory.gethPipecDencity()).orElse(0f));
        Cell cellCVelocity = row.createCell(columnIndex++);
        cellCVelocity.setCellValue(Optional.ofNullable(pipingHistory.gethPipecVelocity()).orElse(0f));


        Cell cellDX = row.createCell(columnIndex++);
        cellDX.setCellValue(Optional.ofNullable(pipingHistory.gethPipedX()).orElse(0f));
        Cell cellDY = row.createCell(columnIndex++);
        cellDY.setCellValue(Optional.ofNullable(pipingHistory.gethPipedY()).orElse(0f));
        Cell cellDV = row.createCell(columnIndex++);
        cellDV.setCellValue(Optional.ofNullable(pipingHistory.gethPipedV()).orElse(0f));



        Cell cellDDensity = row.createCell(columnIndex++);
        cellDDensity.setCellValue(Optional.ofNullable(pipingHistory.gethPipedDencity()).orElse(0f));
        Cell cellDVelocity = row.createCell(columnIndex++);
        cellDVelocity.setCellValue(Optional.ofNullable(pipingHistory.gethPipedVelocity()).orElse(0f));
    }



}
