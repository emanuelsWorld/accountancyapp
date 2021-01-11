package ro.nexttech.internship.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.CompanyDetails;
import ro.nexttech.internship.exception.CompanyNotFound;
import ro.nexttech.internship.service.CallAnafService;
import ro.nexttech.internship.service.FileUploadService;
import ro.nexttech.internship.service.GenerateReportService;


import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private FileUploadService fileUploadService;
    private CallAnafService callAnafService;
    private GenerateReportService generateReportService;

    public FileUploadController(FileUploadService fileUploadService,CallAnafService callAnafService, GenerateReportService generateReportService) {
        this.fileUploadService = fileUploadService;
        this.callAnafService=callAnafService;
        this.generateReportService=generateReportService;
    }

    @PostMapping(value = "/upload/{id}")
    public String uploadDb(@RequestParam("file") MultipartFile multipartFile, @PathVariable Integer id) {
        if (fileUploadService.uploadToDb(multipartFile, id))
            return "<h1>success: file uploaded</h1>";
        else
            return "<h1>failed: invoice doesn't exist</h1>";

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFileDb(@PathVariable Integer id) {
        try {
            Blob blob = fileUploadService.downloadFile(id);
            if (blob != null)
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
                        .body(new ByteArrayResource(blob.getBytes(1, (int) blob.length())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DescriptiveResource("Not found !"));
    }
    @GetMapping("/anaf/{id}")
    public void getCompanyInfo(@PathVariable Integer id) {
        try {
            CompanyDetails companyDetails=callAnafService.call(id);
        } catch (RestClientException | CompanyNotFound e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/report/{firmId}/{monthNumber}")
    public ResponseEntity<InputStreamResource> getReport(@PathVariable("firmId") Integer firmId, @PathVariable("monthNumber") Integer monthNumber) {
        ByteArrayInputStream bais =   generateReportService.generateReport(firmId,monthNumber);
      HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;"+firmId+".pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }
}
