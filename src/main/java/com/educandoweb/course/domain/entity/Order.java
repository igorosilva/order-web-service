package com.educandoweb.course.domain.entity;

import com.educandoweb.course.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.educandoweb.course.util.Constants.TB_ORDER;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldNameConstants
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Table(name = TB_ORDER)
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

    @JsonProperty("items")
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> productList = new HashSet<>();

    @ManyToOne
    private Payment payment;

    public Double total() {
        return this.productList
                .stream()
                .mapToDouble(OrderItem::subTotal)
                .sum();
    }

    public Order(Long id, LocalDateTime _orderedAt, OrderStatus orderStatus, Payment payment) {
        this.id = id;
        this._orderedAt = _orderedAt;
        this.orderStatus = orderStatus;
        this.payment = payment;
    }
}
