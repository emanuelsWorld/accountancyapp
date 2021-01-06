package ro.nexttech.internship.service;

import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.Invoice;

import java.sql.Blob;
import java.util.Set;

public interface FileUploadService {
    public void uploadToLocal(MultipartFile file);
    public void uploadToDb(MultipartFile file);
    public Blob downloadFile(Integer id);
}
