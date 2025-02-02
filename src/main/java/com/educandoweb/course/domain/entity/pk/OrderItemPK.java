package com.educandoweb.course.domain.entity.pk;

import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.domain.entity.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Embeddable
@NoArgsConstructor
public class OrderItemPK implements Serializable {

    private static final long serialVersionUID = -8092076795774758486L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }
}
