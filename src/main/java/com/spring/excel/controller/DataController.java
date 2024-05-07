package com.spring.excel.controller;

import com.spring.excel.entity.Datas;
import com.spring.excel.service.data.DataService;
import com.spring.excel.service.excel.ExcelService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    private DataService dataService;

    @Autowired
    private ExcelService excelService;

    @PostMapping("/save-data-to-excel")
    public ResponseEntity<String> readExcel() {
        try {
            this.dataService.saveAllDatas("C:/Users/admin/Desktop/data.xlsx");
            return ResponseEntity.status(HttpStatus.OK).body("save data success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Datas>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.dataService.getAllDatas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/export-excel")
    public ResponseEntity<String> exportExcel() {
        try {
            this.excelService.importDataFromDbToExcel("D:\\test");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Export data success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
