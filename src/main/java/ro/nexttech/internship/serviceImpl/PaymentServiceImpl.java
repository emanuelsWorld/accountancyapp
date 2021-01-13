package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.repository.PaymentRepository;
import ro.nexttech.internship.service.PaymentService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findById(int id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        return paymentOptional.orElse(null);

    }

    @Override
    public Set<Payment> findAllByIdList(Set<Integer> idList) {

        return idList.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }

}
