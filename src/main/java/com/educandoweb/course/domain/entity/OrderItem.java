package com.educandoweb.course.domain.entity;

import com.educandoweb.course.domain.entity.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Objects;

import static com.educandoweb.course.util.Constants.TB_ORDER_ITEM;

@Setter
@Entity
@NoArgsConstructor
@FieldNameConstants
@Table(name = TB_ORDER_ITEM)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 4575268264182501270L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price);
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}