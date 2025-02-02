package com.educandoweb.course.service;

import com.educandoweb.course.exception.DatabaseException;
import com.educandoweb.course.exception.NotFoundException;
import com.educandoweb.course.repository.GenericRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static com.educandoweb.course.util.Constants.ENTITY_CREATED_SUCCESS;
import static com.educandoweb.course.util.Constants.ENTITY_DELETED_SUCCESS;
import static com.educandoweb.course.util.Constants.ENTITY_DELETING;
import static com.educandoweb.course.util.Constants.ENTITY_FINDING;
import static com.educandoweb.course.util.Constants.ENTITY_FINDING_ALL;
import static com.educandoweb.course.util.Constants.ENTITY_FOUND;
import static com.educandoweb.course.util.Constants.ENTITY_SAVING;
import static com.educandoweb.course.util.Constants.ENTITY_UPDATED_SUCCESS;
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

    public T create(T object, Class<T> clazz) {
        loggingOperation(ENTITY_SAVING, clazz);

        T result = repository.save(object);

        loggingOperation(ENTITY_CREATED_SUCCESS, clazz);

        loggingOperation(OPERATION_SAVE_COMPLETE, clazz);

        return result;
    }

    public T findById(Long id, Class<T> clazz) {
        loggingOperation(ENTITY_FINDING, clazz);

        T object = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

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

        loggingOperation(ENTITY_UPDATED_SUCCESS, clazz);

        return response;
    }

    public void delete(Long id, Class<T> clazz) {
        try {
            findById(id, clazz);

            loggingOperation(ENTITY_DELETING, clazz);

            repository.deleteById(id);

            loggingOperation(ENTITY_DELETED_SUCCESS, clazz);
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    protected void loggingOperation(String message, Class<?> clazz) {
        log.info(getMessage(message, clazz.getSimpleName()));
    }

    protected void loggingError(String message, Class<?> clazz) {
        log.error(getMessage(message, clazz.getSimpleName()));
    }
}