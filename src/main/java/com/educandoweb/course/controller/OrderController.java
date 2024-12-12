package com.educandoweb.course.controller;

import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.service.OrderService;
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
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private static final String CLASS_NAME = OrderController.class.getSimpleName();

    private final OrderService service;

    @PostMapping
    private ResponseEntity<Order> saveOrder(@RequestBody Order request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Order response = service.saveOrder(request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Order response = service.findOrderById(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    private ResponseEntity<List<Order>> findAllOrders() {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        List<Order> response = service.findAllOrders();

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Order response = service.updateOrder(id, request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Order> updateOrder(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        service.deleteOrder(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.noContent().build();
    }
}
