package ro.nexttech.internship.service;

import org.springframework.web.client.RestClientException;
import ro.nexttech.internship.domain.CompanyDetails;
import ro.nexttech.internship.exception.CompanyNotFound;

import java.io.IOException;

public interface CallAnafService {
    CompanyDetails call(Integer CUI) throws RestClientException, CompanyNotFound;
}
