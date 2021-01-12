package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Firm;

import java.util.List;

public interface FirmService {
    List<Firm> findAll(Specification<Firm> specification);
}
