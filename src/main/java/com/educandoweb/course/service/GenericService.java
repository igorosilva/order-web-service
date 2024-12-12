package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.of;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericService<T> {

    private final GenericRepository<T> repository;

    public T save(T object) {
        return repository.save(object);
    }

    public T findById(Long id) {
        return of(repository.findById(id)).get().orElseThrow(RuntimeException::new);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T update(Long id, T object) {
        if(!isExists(id)) {
            return null;
        }

        return repository.save(object);
    }

    public void delete(Long id) {
        if (isExists(id)) {
            repository.deleteById(id);
        }
    }

    protected boolean isExists(Long id) {
        return repository.existsById(id);
    }
}
