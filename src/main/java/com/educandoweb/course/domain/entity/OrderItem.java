package com.educandoweb.course.domain.entity;

import com.educandoweb.course.domain.entity.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

import static com.educandoweb.course.util.Constants.TB_ORDER_ITEM;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_ORDER_ITEM)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 4575268264182501270L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public Double subTotal() {
        return this.price * this.quantity;
    }
}
