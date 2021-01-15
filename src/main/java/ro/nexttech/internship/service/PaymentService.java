package ro.nexttech.internship.service;

import org.springframework.security.core.Authentication;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.dto.PaymentDto;

import java.util.Set;

public interface PaymentService {

    Payment findById(int id);

    Set<Payment> findAllByIdList(Set<Integer> idList);

    PaymentDto getDtoFromPayment (Payment payment);

    Payment getPaymentFromDto(PaymentDto paymentDto);

    PaymentDto savePaymentDto(PaymentDto paymentDto, String userName);

    boolean deleteById(int id);

}
