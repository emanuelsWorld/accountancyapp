package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.repository.FirmRepository;
import ro.nexttech.internship.service.FirmService;

import java.util.List;

@Service
public class FirmServiceImpl implements FirmService {

    private FirmRepository firmRepository;

    @Autowired
    public void setFirmRepository(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    @Override
    public List<Firm> findAll(Specification<Firm> specification) {
        return firmRepository.findAll(specification);
    }
}
