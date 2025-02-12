package com.gl.eirs.ussd.dto;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Data
public class FileDto {

    String fileName;
    String filePath;

    long totalRecords;

    long successRecords;
    long failedRecords;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public FileDto(String fileName, String filePath, long totalRecords, long successRecords, long failedRecords) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.totalRecords = totalRecords;
        this.successRecords = successRecords;
        this.failedRecords = failedRecords;
    }

    public FileDto() {
    }

    public FileDto(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.totalRecords = getFileRecordCount(filePath + "/" + fileName);
        this.successRecords = 0;
        this.failedRecords = 0;
    }


    public long getFileRecordCount(String file) {
        try {
            File file1 = new File(file);
            logger.info("Getting the file size for file {}", file1.toURI());
            Path pathFile = Paths.get(file1.toURI());
            var a  = Files.lines(pathFile).count();
            return a ==0 ? 0 : a-1;
        } catch (Exception e) {
            logger.error("Not able to get the file size for file {}  , {}", file, e);
        }
        return 0L;
    }

}
