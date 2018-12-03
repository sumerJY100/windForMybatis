package com.nw.excel;

import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName WriteDateToExcelTest
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/11/28 13:54
 * @Version 1.0
 */
public class WriteDateToExcelTest {

    @Test
    public void writeDateToExcleTest(){
        WriteDataToExcel writeDateToExcel = new WriteDataToExcel();
        try {
            writeDateToExcel.writeDateToExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
