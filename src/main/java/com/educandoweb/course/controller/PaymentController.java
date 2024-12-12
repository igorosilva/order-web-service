package com.educandoweb.course.controller;

import com.educandoweb.course.domain.entity.Payment;
import com.educandoweb.course.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.educandoweb.course.util.Constants.LOG_INFO;
import static com.educandoweb.course.util.Constants.LOG_KEY_REQUEST;
import static com.educandoweb.course.util.Constants.STEP_END;
import static com.educandoweb.course.util.Constants.STEP_START;
import static com.educandoweb.course.util.Utils.getMethodName;
import static com.educandoweb.course.util.Utils.logBuilder;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private static final String CLASS_NAME = PaymentController.class.getSimpleName();

    private final PaymentService service;

    @PostMapping
    private ResponseEntity<Payment> savePayment(@RequestBody Payment request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Payment response = service.savePayment(request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Payment> findPaymentById(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Payment response = service.findPaymentById(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    private ResponseEntity<List<Payment>> findAllPayments() {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        List<Payment> response = service.findAllPayments();

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Payment response = service.updatePayment(id, request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Payment> updatePayment(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        service.deletePayment(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.noContent().build();
    }
}
