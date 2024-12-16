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
import static com.educandoweb.course.util.Constants.SAVE_OPERATION;
import static com.educandoweb.course.util.Constants.UPDATE_OPERATION;

@Slf4j
@Service
public class CategoryService extends GenericService<Category> {

    private static final Class<Category> CATEGORY_CLASS = Category.class;

    private CategoryRepository repository;

    public CategoryService(GenericRepository<Category> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (CategoryRepository) repository;
    }

    public Category saveCategory(Category request) {
        loggingOperation(OPERATION_START, CATEGORY_CLASS);

        validateCategoryRequest(request, SAVE_OPERATION);

        Category response = create(request, CATEGORY_CLASS);
        
        loggingOperation(OPERATION_END, CATEGORY_CLASS);

        return response;
    }

    public Category findCategoryById(Long id) {
        loggingOperation(OPERATION_START, CATEGORY_CLASS);

        Category response = findById(id, CATEGORY_CLASS);
        
        loggingOperation(OPERATION_END, CATEGORY_CLASS);
        
        return response;
    }

    public List<Category> findAllCategorys() {
        loggingOperation(OPERATION_START, CATEGORY_CLASS);

        List<Category> response = findAll(CATEGORY_CLASS);
        
        loggingOperation(OPERATION_END, CATEGORY_CLASS);
        
        return response;
    }

    public Category updateCategory(Long id, Category request) {
        loggingOperation(OPERATION_START, CATEGORY_CLASS);

        validateCategoryRequest(request, UPDATE_OPERATION);

        Category response = update(id, request, CATEGORY_CLASS);

        loggingOperation(OPERATION_END, CATEGORY_CLASS);

        return response;
    }

    public void deleteCategory(Long id) {
        loggingOperation(OPERATION_START, CATEGORY_CLASS);

        delete(id, CATEGORY_CLASS);

        loggingOperation(OPERATION_END, CATEGORY_CLASS);
    }

    private void validateCategoryRequest(Category request, String operation) {
        String categoryName = request.getDsName();
        boolean categoryNameIsEmpty = categoryName.isEmpty() || categoryName.isBlank();

        if(categoryNameIsEmpty) {
            loggingError("operation." + operation + ".fail", CATEGORY_CLASS);
            throw new RuntimeException("Category name can't be empty");
        }

        if(repository.existsByName(categoryName)) {
            loggingError("operation." + operation + ".fail", CATEGORY_CLASS);
            throw new RuntimeException("Category name already exists");
        }
    }
}
