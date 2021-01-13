package ro.nexttech.internship.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import ro.nexttech.internship.domain.CompanyDetails;
import ro.nexttech.internship.exception.CompanyNotFound;
import ro.nexttech.internship.service.CallAnafService;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/anaf")
public class CallAnafController {
    private CallAnafService callAnafService;

    public CallAnafController(CallAnafService callAnafService) {
        this.callAnafService = callAnafService;
    }

    @GetMapping("/{id}")
    public void getCompanyInfo(@PathVariable Integer id) {
        try {
            CompanyDetails companyDetails = callAnafService.call(id);
        } catch (RestClientException | CompanyNotFound e) {
            e.printStackTrace();
        }

    }
}
