package com.spring.excel.service.excel;

import com.spring.excel.entity.Datas;

import java.util.List;

public interface ExcelService {
    public List<Datas> getDatafromExcel(String fileLocation);
}
