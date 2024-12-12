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

    private PaymentRepository repository;

    public PaymentService(GenericRepository<Payment> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (PaymentRepository) repository;
    }

    public Payment savePayment(Payment request) {
        loggingOperation(OPERATION_START, Payment.class);

        Payment response = save(request, Payment.class);
        
        loggingOperation(OPERATION_END, Payment.class);

        return response;
    }

    public Payment findPaymentById(Long id) {
        loggingOperation(OPERATION_START, Payment.class);

        Payment response = findById(id, Payment.class);
        
        loggingOperation(OPERATION_END, Payment.class);
        
        return response;
    }

    public List<Payment> findAllPayments() {
        loggingOperation(OPERATION_START, Payment.class);

        List<Payment> response = findAll(Payment.class);
        
        loggingOperation(OPERATION_END, Payment.class);
        
        return response;
    }

    public Payment updatePayment(Long id, Payment request) {
        loggingOperation(OPERATION_START, Payment.class);

        Payment response = update(id, request, Payment.class);

        loggingOperation(OPERATION_END, Payment.class);

        return response;
    }

    public void deletePayment(Long id) {
        loggingOperation(OPERATION_START, Payment.class);

        delete(id, Payment.class);

        loggingOperation(OPERATION_END, Payment.class);
    }
}
