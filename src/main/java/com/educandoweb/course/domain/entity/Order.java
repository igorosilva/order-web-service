package com.educandoweb.course.domain.entity;

import com.educandoweb.course.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

import static com.educandoweb.course.util.Constants.TB_ORDER;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.CascadeType.ALL;
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
    @JsonProperty("code")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JsonProperty("orderedAt")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime _orderedAt;

    @JsonProperty("status")
    private OrderStatus orderStatus;

    @JsonIgnore
    @JsonProperty("items")
    @OneToMany(mappedBy = "id.order", cascade = ALL, orphanRemoval = true)
    private List<OrderItem> productList;

    @ManyToOne
    @JsonIgnore
    @JsonProperty("client")
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToOne
    private Payment payment;

    public Double total() {
        return this.productList
                .stream()
                .mapToDouble(OrderItem::subTotal)
                .sum();
    }
}
