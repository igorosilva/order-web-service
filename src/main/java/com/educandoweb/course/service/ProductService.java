package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Product;
import com.educandoweb.course.exception.IllegalArgumentException;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.ProductRepository;
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
public class ProductService extends GenericService<Product> {

    private static final Class<Product> PRODUCT_CLASS = Product.class;

    private ProductRepository repository;

    public ProductService(GenericRepository<Product> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (ProductRepository) repository;
    }

    public Product saveProduct(Product request) {
        loggingOperation(OPERATION_START, PRODUCT_CLASS);

        validateProductRequest(request, SAVE_OPERATION);

        Product response = create(request, PRODUCT_CLASS);
        
        loggingOperation(OPERATION_END, PRODUCT_CLASS);

        return response;
    }

    public Product findProductById(Long id) {
        loggingOperation(OPERATION_START, PRODUCT_CLASS);

        Product response = findById(id, PRODUCT_CLASS);
        
        loggingOperation(OPERATION_END, PRODUCT_CLASS);
        
        return response;
    }

    public List<Product> findAllProducts() {
        loggingOperation(OPERATION_START, PRODUCT_CLASS);

        List<Product> response = findAll(PRODUCT_CLASS);
        
        loggingOperation(OPERATION_END, PRODUCT_CLASS);
        
        return response;
    }

    public Product updateProduct(Long id, Product request) {
        loggingOperation(OPERATION_START, PRODUCT_CLASS);

        validateProductRequest(request, UPDATE_OPERATION);

        Product response = update(id, request, PRODUCT_CLASS);

        loggingOperation(OPERATION_END, PRODUCT_CLASS);

        return response;
    }

    public void deleteProduct(Long id) {
        loggingOperation(OPERATION_START, PRODUCT_CLASS);

        delete(id, PRODUCT_CLASS);

        loggingOperation(OPERATION_END, PRODUCT_CLASS);
    }

    private void validateProductRequest(Product request, String operation) {
        String productName = request.getNmProduct();
        boolean productNameIsEmpty = productName.isBlank() || productName.isEmpty();
        String logErrorMessage = "operation." + operation + ".fail";

        if(request.getVlPrice() <= 0) {
            loggingError(logErrorMessage, PRODUCT_CLASS);
            throw new IllegalArgumentException("The price can't be less or equal to 0");
        }

        if(productNameIsEmpty) {
            loggingError(logErrorMessage, PRODUCT_CLASS);
            throw new IllegalArgumentException("The product name can't be empty");
        }

        if(request.getOrderList().isEmpty()) {
            loggingError(logErrorMessage, PRODUCT_CLASS);
            throw new IllegalArgumentException("The order list can't be empty");
        }
    }
}
