package com.educandoweb.course.controller;

import com.educandoweb.course.domain.entity.Product;
import com.educandoweb.course.service.ProductService;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private static final String CLASS_NAME = ProductController.class.getSimpleName();

    private final ProductService service;

    @PostMapping
    private ResponseEntity<Product> saveProduct(@RequestBody Product request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Product response = service.saveProduct(request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> findProductById(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Product response = service.findProductById(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    private ResponseEntity<List<Product>> findAllProducts() {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        List<Product> response = service.findAllProducts();

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Product response = service.updateProduct(id, request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Product> updateProduct(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        service.deleteProduct(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.noContent().build();
    }
}
