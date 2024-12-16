package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.domain.enums.OrderStatus;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.educandoweb.course.domain.enums.OrderStatus.PAID;
import static com.educandoweb.course.domain.enums.OrderStatus.WAITING_PAYMENT;
import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_START;

@Slf4j
@Service
public class OrderService extends GenericService<Order> {

    private static final Class<Order> ORDER_CLASS = Order.class;

    private OrderRepository repository;

    public OrderService(GenericRepository<Order> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (OrderRepository) repository;
    }

    public Order saveOrder(Order request) {
        loggingOperation(OPERATION_START, ORDER_CLASS);

        request.setOrderStatus(WAITING_PAYMENT);

        Order response = create(request, ORDER_CLASS);
        
        loggingOperation(OPERATION_END, ORDER_CLASS);

        return response;
    }

    public Order findOrderById(Long id) {
        loggingOperation(OPERATION_START, ORDER_CLASS);

        Order response = findById(id, ORDER_CLASS);
        
        loggingOperation(OPERATION_END, ORDER_CLASS);
        
        return response;
    }

    public List<Order> findAllOrders() {
        loggingOperation(OPERATION_START, ORDER_CLASS);

        List<Order> response = findAll(ORDER_CLASS);
        
        loggingOperation(OPERATION_END, ORDER_CLASS);
        
        return response;
    }

    public Order updateOrder(Long id, Order request) {
        loggingOperation(OPERATION_START, ORDER_CLASS);

        if(PAID.equals(request.getOrderStatus())) {
            request.getPayment().setPaidAt(LocalDateTime.now());
        }

        Order response = update(id, request, ORDER_CLASS);

        loggingOperation(OPERATION_END, ORDER_CLASS);

        return response;
    }

    public void deleteOrder(Long id) {
        loggingOperation(OPERATION_START, ORDER_CLASS);

        delete(id, ORDER_CLASS);

        loggingOperation(OPERATION_END, ORDER_CLASS);
    }
}
