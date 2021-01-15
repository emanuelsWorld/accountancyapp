package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.exception.FirmNotFoundException;
import ro.nexttech.internship.repository.FirmRepository;
import ro.nexttech.internship.service.FirmService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Firm findById(int id) {
        Optional<Firm> firmOptional = firmRepository.findById(id);

        if (firmOptional.isPresent()) {
            return firmOptional.get();
        } else
            return null;
    }

    @Override
    public Firm findFirmByName(String firmName) throws FirmNotFoundException {
        Optional<Firm> firmOptional = firmRepository.findByFirmName(firmName);
        if (firmOptional.isPresent()) {
            return firmOptional.get();
        } else
            throw new FirmNotFoundException("Firm not found");
    }
}



