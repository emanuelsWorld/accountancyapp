package ro.nexttech.internship.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import ro.nexttech.internship.domain.CompanyDetails;
import ro.nexttech.internship.exception.CompanyNotFound;
import ro.nexttech.internship.service.CallAnafService;


@RestController
@RequestMapping("/anaf")
public class CallAnafController {
    private CallAnafService callAnafService;

    public CallAnafController(CallAnafService callAnafService) {
        this.callAnafService = callAnafService;
    }

    @GetMapping("/{CUI}")
    public void getCompanyInfo(@PathVariable Integer CUI) {
        try {
            CompanyDetails companyDetails = callAnafService.call(CUI);
        } catch (RestClientException | CompanyNotFound e) {
            e.printStackTrace();
        }

    }
}
