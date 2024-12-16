package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Payment;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_START;

@Slf4j
@Service
public class PaymentService extends GenericService<Payment> {

    private final static Class<Payment> PAYMENT_CLASS = Payment.class;

    private PaymentRepository repository;

    public PaymentService(GenericRepository<Payment> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (PaymentRepository) repository;
    }

    public Payment savePayment(Payment request) {
        loggingOperation(OPERATION_START, PAYMENT_CLASS);

        Payment response = create(request, PAYMENT_CLASS);
        
        loggingOperation(OPERATION_END, PAYMENT_CLASS);

        return response;
    }

    public Payment findPaymentById(Long id) {
        loggingOperation(OPERATION_START, PAYMENT_CLASS);

        Payment response = findById(id, PAYMENT_CLASS);
        
        loggingOperation(OPERATION_END, PAYMENT_CLASS);
        
        return response;
    }

    public List<Payment> findAllPayments() {
        loggingOperation(OPERATION_START, PAYMENT_CLASS);

        List<Payment> response = findAll(PAYMENT_CLASS);
        
        loggingOperation(OPERATION_END, PAYMENT_CLASS);
        
        return response;
    }

    public Payment updatePayment(Long id, Payment request) {
        loggingOperation(OPERATION_START, PAYMENT_CLASS);

        Payment response = update(id, request, PAYMENT_CLASS);

        loggingOperation(OPERATION_END, PAYMENT_CLASS);

        return response;
    }

    public void deletePayment(Long id) {
        loggingOperation(OPERATION_START, PAYMENT_CLASS);

        delete(id, PAYMENT_CLASS);

        loggingOperation(OPERATION_END, PAYMENT_CLASS);
    }
}
