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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.educandoweb.course.util.Constants.TB_PAYMENT;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
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
@Table(name = TB_PAYMENT)
public class Payment implements Serializable {

    private static final long serialVersionUID = -4186779048552394362L;

    @Id
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

    public LocalDateTime getPaidAt() {
        return this._paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this._paidAt = paidAt;
    }
}
