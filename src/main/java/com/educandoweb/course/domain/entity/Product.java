package com.educandoweb.course.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.List;

import static com.educandoweb.course.util.Constants.TB_PRODUCT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_PRODUCT)
public class Product implements Serializable {

    private static final long serialVersionUID = 4336149754351482717L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty("code")
    private Long id;

    @JsonProperty("name")
    private String dsName;

    @JsonProperty("description")
    private String dsProduct;

    @JsonProperty("price")
    private double vlPrice;

    @JsonProperty("image")
    private String imgUrl;

    @ManyToMany
    @JsonProperty("categories")
    private List<Category> categoryList;

    @ManyToMany
    @JsonProperty("orders")
    private List<Order> orderList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonProperty("orders")
    private List<OrderItem> orderItems;
}
