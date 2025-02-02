package com.educandoweb.course.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.educandoweb.course.util.Constants.TB_PAYMENT;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Entity
@Builder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_PAYMENT)
public class Payment implements Serializable {

    private static final long serialVersionUID = -4186779048552394362L;

    @Id
    @Include
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty("code")
    private Long id;

    @JsonProperty("paymentMoment")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime _paidAt;

    @OneToOne
    @JsonIgnore
    @JsonProperty("order")
    private Order order;

    public Payment(Long id, LocalDateTime _paidAt, Order order) {
        this.id = id;
        this._paidAt = _paidAt;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getPaidAt() {
        return _paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this._paidAt = paidAt;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(_paidAt, payment._paidAt) && Objects.equals(order, payment.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, _paidAt, order);
    }
}
