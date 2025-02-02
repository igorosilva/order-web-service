package com.educandoweb.course.domain.entity;

import com.educandoweb.course.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.educandoweb.course.util.Constants.TB_ORDER;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Entity
@Builder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_ORDER)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {

    private static final long serialVersionUID = -8902304584597040749L;

    @Id
    @Include
    @JsonProperty("code")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JsonProperty("orderedAt")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime _orderedAt;

    @JsonProperty("status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @JsonProperty("items")
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> productList = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = ALL)
    private Payment payment;

    public Order(Long id, LocalDateTime _orderedAt, OrderStatus orderStatus, Payment payment) {
        this.id = id;
        this._orderedAt = _orderedAt;
        this.orderStatus = orderStatus;
        this.payment = payment;
    }

    public Order(Long id, LocalDateTime _orderedAt, OrderStatus orderStatus, User user, Set<OrderItem> productList, Payment payment) {
        this(id, _orderedAt, orderStatus, payment);
        this.client = user;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime get_orderedAt() {
        return _orderedAt;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public User getClient() {
        return client;
    }

    public Set<OrderItem> getProductList() {
        return productList;
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(_orderedAt, order._orderedAt) && orderStatus == order.orderStatus && Objects.equals(client, order.client) && Objects.equals(productList, order.productList) && Objects.equals(payment, order.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, _orderedAt, orderStatus, client, productList, payment);
    }

    public Double getTotal() {
        return this.productList
                .stream()
                .mapToDouble(OrderItem::getSubTotal)
                .sum();
    }
}
