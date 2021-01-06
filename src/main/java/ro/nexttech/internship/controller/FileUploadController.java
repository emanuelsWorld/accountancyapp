package ro.nexttech.internship.controller;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.service.FileUploadService;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("api")
public class FileUploadController {


    private FileUploadService fileUploadService;


    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService=fileUploadService;
    }

    @PostMapping("/upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile multipartFile) {
        fileUploadService.uploadToLocal(multipartFile);
    }

    @PostMapping("/upload/db")
    public void uploadDb(@RequestParam("file")MultipartFile multipartFile) {
        fileUploadService.uploadToDb(multipartFile);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFileDb(@PathVariable Integer id) {
        Blob blob=null;
        try {
           Blob blob2= new SerialBlob(fileUploadService.downloadFile(id));
           blob = blob2;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;")
                    .body(new ByteArrayResource(blob.getBytes(1,(int)blob.length())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
