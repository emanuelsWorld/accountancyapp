package ro.nexttech.internship.service;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import ro.nexttech.internship.domain.CompanyDetails;
import ro.nexttech.internship.exception.CompanyNotFound;

public interface CallAnafService {
    CompanyDetails call(Integer CUI) throws RestClientException, CompanyNotFound;
    HttpEntity<String> generateRequest(Integer CUI);
}
