package com.spring.excel.service.excel;

import com.spring.excel.entity.Datas;
import com.spring.excel.repository.DataRepository;
import com.spring.excel.service.data.DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService{
    @Autowired
    private DataRepository dataRepository;


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
            workbook.close();
            fis.close();
            return listData;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void importDataFromDbToExcel(String fileLocation) {
        try(FileOutputStream fos = new FileOutputStream(fileLocation+"data.xls")) {
            List<Datas> lstDatas = dataRepository.findAll();

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Name");
            row.createCell(1).setCellValue("Age");
            row.createCell(2).setCellValue("Marry");

            int dataRowIndex = 1;
            for (Datas data : lstDatas) {
                Row dataRow = sheet.createRow(dataRowIndex);
                dataRow.createCell(0).setCellValue(data.getName());
                dataRow.createCell(1).setCellValue(data.getAge());
                if(data.isMarry()){
                    dataRow.createCell(2).setCellValue("YES");
                }else {
                    dataRow.createCell(2).setCellValue("NO");
                }
                dataRowIndex++;
            }

            workbook.write(fos);
            workbook.close();
            fos.close();
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
