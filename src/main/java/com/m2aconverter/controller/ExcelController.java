package com.m2aconverter.controller;

import com.m2aconverter.entity.Action;
import com.m2aconverter.service.ExcelService;
import com.m2aconverter.service.NlpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private NlpService nlpService;

    @PostMapping
    public ResponseEntity<byte[]> saveExcel(@RequestParam("file") MultipartFile file) {
        return excelService.saveExcelToFile(file);
    }

    @GetMapping
    public ResponseEntity<String> getNlp(@RequestBody Action action) {
        return nlpService.getNlpByAction(action);
    }
}