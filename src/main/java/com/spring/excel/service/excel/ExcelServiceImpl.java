package com.spring.excel.service.excel;

import com.spring.excel.entity.Datas;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService{

    @Override
    public List<Datas> getDatafromExcel(String fileLocation) {
        List<Datas> listData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileLocation)) {

            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row != null) {
                    Datas datas = new Datas();
                    datas.setName(row.getCell(0).toString());
                    datas.setAge(row.getCell(1).toString().split("\\.")[0]);
                    if ("YES".equals(row.getCell(2).toString())) {
                        datas.setMarry(true);
                    } else {
                        datas.setMarry(false);
                    }

                    listData.add(datas);
                }
            }
            return listData;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
