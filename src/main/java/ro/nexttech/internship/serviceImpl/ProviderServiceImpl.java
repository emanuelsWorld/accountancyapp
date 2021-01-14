package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Provider;
import ro.nexttech.internship.repository.ProviderRepository;
import ro.nexttech.internship.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepository providerRepository;

    @Autowired
    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Provider findProviderById(int id) {
        return providerRepository.findById(id).orElse(null);
    }
}
