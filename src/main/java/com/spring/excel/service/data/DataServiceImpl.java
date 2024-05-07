    package com.spring.excel.service.data;

    import com.spring.excel.entity.Datas;
    import com.spring.excel.repository.DataRepository;
    import com.spring.excel.service.excel.ExcelService;
    import lombok.AllArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.apache.poi.ss.usermodel.Row;
    import org.apache.poi.ss.usermodel.Sheet;
    import org.apache.poi.ss.usermodel.Workbook;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.io.FileInputStream;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    @Service
    @Slf4j
    public class DataServiceImpl implements DataService {

        @Autowired
        private DataRepository dataRepository;

        @Autowired
        private ExcelService excelService;

        @Override
        public List<Datas> getAllDatas() {
            try {
                return this.dataRepository.findAll();
            }catch (Exception e){
                log.error(e.getMessage());
                return Collections.emptyList();
            }
        }

        @Override
        public void saveAllDatas(String fileLocation) {
            try {
                this.dataRepository.saveAll(excelService.getDatafromExcel(fileLocation));
            }catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
