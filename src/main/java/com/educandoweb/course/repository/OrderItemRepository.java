package com.educandoweb.course.repository;

import com.educandoweb.course.domain.entity.OrderItem;
import com.educandoweb.course.domain.entity.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}