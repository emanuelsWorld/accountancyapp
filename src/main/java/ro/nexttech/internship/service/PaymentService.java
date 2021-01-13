package ro.nexttech.internship.service;

import ro.nexttech.internship.domain.Payment;

import java.util.Set;

public interface PaymentService {

    Payment findById(int id);

    Set<Payment> findAllByIdList(Set<Integer> idList);

}
