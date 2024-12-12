package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

import static com.educandoweb.course.util.Constants.ENTITY_CREATED_SUCCESS;
import static com.educandoweb.course.util.Constants.ENTITY_DELETING;
import static com.educandoweb.course.util.Constants.ENTITY_EXISTS;
import static com.educandoweb.course.util.Constants.ENTITY_FINDING;
import static com.educandoweb.course.util.Constants.ENTITY_FINDING_ALL;
import static com.educandoweb.course.util.Constants.ENTITY_FOUND;
import static com.educandoweb.course.util.Constants.ENTITY_NOT_FOUND_BY_ID;
import static com.educandoweb.course.util.Constants.ENTITY_SAVING;
import static com.educandoweb.course.util.Constants.OPERATION_SAVE_COMPLETE;
import static com.educandoweb.course.util.Constants.OPERATION_SEARCH_COMPLETE;

@Slf4j
@RequiredArgsConstructor
public abstract class GenericService<T> {

    private final GenericRepository<T> repository;
    protected final MessageSource messageSource;

    protected String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public T save(T object, Class<T> clazz) {
        loggingOperation(ENTITY_SAVING, clazz);

        T result = repository.save(object);

        loggingOperation(ENTITY_CREATED_SUCCESS, clazz);

        loggingOperation(OPERATION_SAVE_COMPLETE, clazz);

        return result;
    }

    public T findById(Long id, Class<T> clazz) {
        loggingOperation(ENTITY_FINDING, clazz);

        T object = repository.findById(id).orElseThrow(() -> {
            String message = getMessage(ENTITY_NOT_FOUND_BY_ID);
            log.error(message);
            return new RuntimeException(message);
        });

        loggingOperation(ENTITY_FOUND, clazz);

        loggingOperation(OPERATION_SEARCH_COMPLETE, clazz);

        return object;
    }

    public List<T> findAll(Class<T> clazz) {
        loggingOperation(ENTITY_FINDING_ALL, clazz);

        List<T> response = repository.findAll();

        loggingOperation(OPERATION_SEARCH_COMPLETE, clazz);

        return response;
    }

    public T update(Long id, T object, Class<T> clazz) {
        findById(id, clazz);

        loggingOperation(ENTITY_SAVING, clazz);

        T response = repository.save(object);

        loggingOperation(OPERATION_SAVE_COMPLETE, clazz);

        return response;
    }

    public void delete(Long id, Class<T> clazz) {
        findById(id, clazz);

        loggingOperation(ENTITY_DELETING, clazz);

        repository.deleteById(id);
    }

    protected void loggingOperation(String message, Class<?> clazz) {
        log.info(getMessage(message, clazz.getSimpleName()));
    }
}