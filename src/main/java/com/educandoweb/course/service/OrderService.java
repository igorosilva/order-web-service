package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_START;

@Slf4j
@Service
public class OrderService extends GenericService<Order> {

    private OrderRepository repository;

    public OrderService(GenericRepository<Order> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (OrderRepository) repository;
    }

    public Order saveOrder(Order request) {
        loggingOperation(OPERATION_START, Order.class);

        Order response = save(request, Order.class);
        
        loggingOperation(OPERATION_END, Order.class);

        return response;
    }

    public Order findOrderById(Long id) {
        loggingOperation(OPERATION_START, Order.class);

        Order response = findById(id, Order.class);
        
        loggingOperation(OPERATION_END, Order.class);
        
        return response;
    }

    public List<Order> findAllOrders() {
        loggingOperation(OPERATION_START, Order.class);

        List<Order> response = findAll(Order.class);
        
        loggingOperation(OPERATION_END, Order.class);
        
        return response;
    }

    public Order updateOrder(Long id, Order request) {
        loggingOperation(OPERATION_START, Order.class);

        Order response = update(id, request, Order.class);

        loggingOperation(OPERATION_END, Order.class);

        return response;
    }

    public void deleteOrder(Long id) {
        loggingOperation(OPERATION_START, Order.class);

        delete(id, Order.class);

        loggingOperation(OPERATION_END, Order.class);
    }
}
