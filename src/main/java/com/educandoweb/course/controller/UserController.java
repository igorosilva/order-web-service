package com.educandoweb.course.controller;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String CLASS_NAME = UserController.class.getSimpleName();

    private final UserService service;

    @PostMapping
    private ResponseEntity<User> saveUser(@RequestBody User request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        User response = service.saveUser(request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> findUserById(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        User response = service.findUserById(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        if(response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    private ResponseEntity<List<User>> findAllUsers() {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        List<User> response = service.findAllUsers();

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User request) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        User response = service.updateUser(id, request);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        if(response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<User> updateUser(@PathVariable Long id) {
        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_START);

        service.deleteUser(id);

        log.info(logBuilder(LOG_INFO, LOG_KEY_REQUEST), CLASS_NAME, getMethodName(), STEP_END);

        return ResponseEntity.noContent().build();
    }
}
