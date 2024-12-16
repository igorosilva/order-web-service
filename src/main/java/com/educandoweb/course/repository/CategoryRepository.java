package com.educandoweb.course.repository;

import com.educandoweb.course.domain.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {
    boolean existsByName(String name);
}
