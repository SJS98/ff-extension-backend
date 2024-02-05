package com.m2aconverter.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.EmptyFileException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ExcelService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<byte[]> saveExcelToFile(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            log.info(cell.toString());

        } catch (EmptyFileException e) {
            log.error("Excel file is empty");
        } catch (IOException e) {
            log.error("Error occurred during reading file");
        } catch (EncryptedDocumentException e) {
            log.error("Can't read protected document");
        }
        return null;
    }
}