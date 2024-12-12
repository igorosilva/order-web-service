package com.educandoweb.course.controller;

import com.educandoweb.course.domain.entity.Category;
import com.educandoweb.course.service.CategoryService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private static final String CLASS_NAME = CategoryController.class.getSimpleName();

    private final CategoryService service;

    @PostMapping
    private ResponseEntity<Category> saveCategory(@RequestBody Category request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Category response = service.saveCategory(request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Category response = service.findCategoryById(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    private ResponseEntity<List<Category>> findAllCategorys() {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        List<Category> response = service.findAllCategorys();

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        Category response = service.updateCategory(id, request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Category> updateCategory(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        service.deleteCategory(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.noContent().build();
    }
}
