package com.spring.excel.service.data;

import com.spring.excel.entity.Datas;

import java.util.List;

public interface DataService {
    public List<Datas> getAllDatas();
    public void saveAllDatas(String fileLocation);
}
