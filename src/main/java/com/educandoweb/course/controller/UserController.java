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

import static com.educandoweb.course.util.Constants.CLASS_NAME;
import static com.educandoweb.course.util.Constants.METHOD_NAME;
import static com.educandoweb.course.util.Utils.logBuilder;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String className = UserController.class.getSimpleName();
    private static final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

    private final UserService service;

    @PostMapping
    private ResponseEntity<User> saveUser(@RequestBody User request) {
        log.info(logBuilder(CLASS_NAME, className, METHOD_NAME, methodName));

        User response = (User) service.saveUser(request);

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> findUserById(@PathVariable Long id) {
        log.info(logBuilder(CLASS_NAME, className, METHOD_NAME, methodName));

        User response = service.findUserById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    private ResponseEntity<List<User>> findAllUsers() {
        log.info(logBuilder(CLASS_NAME, className, METHOD_NAME, methodName));

        List<User> response = service.findAllUsers();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User request) {
        log.info(logBuilder(CLASS_NAME, className, METHOD_NAME, methodName));

        User response = service.updateUser(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<User> updateUser(@PathVariable Long id) {
        log.info(logBuilder(CLASS_NAME, className, METHOD_NAME, methodName));

        service.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
