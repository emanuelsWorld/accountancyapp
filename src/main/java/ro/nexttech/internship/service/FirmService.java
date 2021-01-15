package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.exception.FirmNotFoundException;

import java.util.List;

public interface FirmService {

    List<Firm> findAll(Specification<Firm> specification);

    Firm findById(int id);

    Firm findFirmByName(String firmName) throws FirmNotFoundException;

}
