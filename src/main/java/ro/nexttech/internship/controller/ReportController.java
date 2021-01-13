package ro.nexttech.internship.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nexttech.internship.service.GenerateReportService;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/report")
public class ReportController {
    private GenerateReportService generateReportService;
    public ReportController(GenerateReportService generateReportService) {
        this.generateReportService=generateReportService;
    }
    @GetMapping("/{firmId}/{monthNumber}")
    public ResponseEntity<InputStreamResource> getReport(@PathVariable("firmId") Integer firmId, @PathVariable("monthNumber") Integer monthNumber) {
        ByteArrayInputStream bais =   generateReportService.generateReport(firmId,monthNumber);
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;"+firmId+".pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }
}
