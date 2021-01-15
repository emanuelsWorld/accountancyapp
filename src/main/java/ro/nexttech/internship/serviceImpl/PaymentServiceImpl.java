package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.PaymentDto;
import ro.nexttech.internship.repository.PaymentRepository;
import ro.nexttech.internship.repository.UserRepository;
import ro.nexttech.internship.service.InvoiceService;
import ro.nexttech.internship.service.PaymentService;
import ro.nexttech.internship.service.UserService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private UserService userService;
    private PaymentRepository paymentRepository;
    private InvoiceService invoiceService;

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
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

    @Override
    public PaymentDto getDtoFromPayment(Payment payment) {

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(payment.getPaymentId());
        paymentDto.setAmmount(payment.getAmmount());
        paymentDto.setDate(payment.getDate());
        paymentDto.setType(payment.getType());
        paymentDto.setPaymentNumber(payment.getPaymentNumber());
        paymentDto.setInvoiceEntities(payment.getInvoiceEntities()
                .stream().map(Invoice::getInvoiceId).collect(Collectors.toSet()));
        System.out.println("Nr: " + paymentDto.getPaymentNumber() + "  Type:" + paymentDto.getType().toString());
        return paymentDto;
    }

    @Override
    public Payment getPaymentFromDto(PaymentDto paymentDto) {

        Payment payment = new Payment();
        payment.setPaymentId(paymentDto.getPaymentId());
        payment.setAmmount(paymentDto.getAmmount());
        payment.setDate(paymentDto.getDate());
        payment.setPaymentNumber(paymentDto.getPaymentNumber());
        payment.setType(paymentDto.getType());
        payment.setInvoiceEntities(invoiceService.findAllByIdSet(paymentDto.getInvoiceEntities()));

        return payment;
    }

    @Override
    public PaymentDto savePaymentDto(PaymentDto paymentDto, String userName) {

        User user = userService.findByUserName(userName);
        Payment payment = getPaymentFromDto(paymentDto);
        if (user.getFirm() == payment.getInvoiceEntities().iterator().next().getFirm()) {
            paymentRepository.save(payment);
            return paymentDto;
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            paymentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
