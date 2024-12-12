package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Product;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educandoweb.course.util.Constants.ENTITY_EXISTS;
import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_START;

@Slf4j
@Service
public class ProductService extends GenericService<Product> {

    private ProductRepository repository;

    public ProductService(GenericRepository<Product> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (ProductRepository) repository;
    }

    public Product saveProduct(Product request) {
        loggingOperation(OPERATION_START, Product.class);

        Product response = save(request, Product.class);
        
        loggingOperation(OPERATION_END, Product.class);

        return response;
    }

    public Product findProductById(Long id) {
        loggingOperation(OPERATION_START, Product.class);

        Product response = findById(id, Product.class);
        
        loggingOperation(OPERATION_END, Product.class);
        
        return response;
    }

    public List<Product> findAllProducts() {
        loggingOperation(OPERATION_START, Product.class);

        List<Product> response = findAll(Product.class);
        
        loggingOperation(OPERATION_END, Product.class);
        
        return response;
    }

    public Product updateProduct(Long id, Product request) {
        loggingOperation(OPERATION_START, Product.class);

        Product response = update(id, request, Product.class);

        loggingOperation(OPERATION_END, Product.class);

        return response;
    }

    public void deleteProduct(Long id) {
        loggingOperation(OPERATION_START, Product.class);

        delete(id, Product.class);

        loggingOperation(OPERATION_END, Product.class);
    }
}
