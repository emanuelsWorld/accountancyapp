package ro.nexttech.internship.service;

import org.springframework.web.multipart.MultipartFile;


import java.sql.Blob;


public interface FileUploadService {
    boolean uploadToDb(MultipartFile file, Integer id);
    Blob downloadFile(Integer id);
}
