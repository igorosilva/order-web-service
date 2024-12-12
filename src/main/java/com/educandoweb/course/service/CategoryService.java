package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Category;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_START;

@Slf4j
@Service
public class CategoryService extends GenericService<Category> {

    private CategoryRepository repository;

    public CategoryService(GenericRepository<Category> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (CategoryRepository) repository;
    }

    public Category saveCategory(Category request) {
        loggingOperation(OPERATION_START, Category.class);

        Category response = save(request, Category.class);
        
        loggingOperation(OPERATION_END, Category.class);

        return response;
    }

    public Category findCategoryById(Long id) {
        loggingOperation(OPERATION_START, Category.class);

        Category response = findById(id, Category.class);
        
        loggingOperation(OPERATION_END, Category.class);
        
        return response;
    }

    public List<Category> findAllCategorys() {
        loggingOperation(OPERATION_START, Category.class);

        List<Category> response = findAll(Category.class);
        
        loggingOperation(OPERATION_END, Category.class);
        
        return response;
    }

    public Category updateCategory(Long id, Category request) {
        loggingOperation(OPERATION_START, Category.class);

        Category response = update(id, request, Category.class);

        loggingOperation(OPERATION_END, Category.class);

        return response;
    }

    public void deleteCategory(Long id) {
        loggingOperation(OPERATION_START, Category.class);

        delete(id, Category.class);

        loggingOperation(OPERATION_END, Category.class);
    }
}
