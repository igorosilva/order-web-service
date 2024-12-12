package com.educandoweb.course.repository;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository<T> extends JpaRepository<T, Long> {

    boolean existsById(@NonNull Long id);
}
