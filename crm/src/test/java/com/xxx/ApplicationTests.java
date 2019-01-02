package com.xxx;

import com.xxx.common.util.EhcacheUtil;
import com.xxx.common.util.ImportExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {
        EhcacheUtil.put("username","xxx");
        System.out.println(EhcacheUtil.get("username"));
    }

    @Test
    public void importExcel() throws Exception {

    }

}