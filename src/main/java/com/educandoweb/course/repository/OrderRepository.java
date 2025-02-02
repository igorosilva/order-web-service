package com.educandoweb.course.repository;

import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.domain.enums.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order> {
    boolean existsByClientAndOrderStatus(User client, OrderStatus orderStatus);
}
