package com.educandoweb.course.domain.entity;

import com.educandoweb.course.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.educandoweb.course.util.Constants.TB_ORDER;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@FieldNameConstants
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Table(name = TB_ORDER)
public class Order implements Serializable {

    private static final long serialVersionUID = -8902304584597040749L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty("code")
    private Long id;

    @JsonProperty("moment")
    private LocalDateTime _orderedAt;

    @JsonProperty("status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("items")
    private List<OrderItem> productList;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonProperty("client")
    private User user;

    @ManyToOne
    @JsonProperty("payment")
    private Payment paymentMethod;

    public Double total() {
        return this.productList
                .stream()
                .mapToDouble(OrderItem::subTotal)
                .sum();
    }
}
