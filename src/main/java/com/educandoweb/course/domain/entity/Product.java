package com.educandoweb.course.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
import java.util.HashSet;
import java.util.Set;

import static com.educandoweb.course.util.Constants.TB_PRODUCT;
import static com.educandoweb.course.util.Constants.TB_PRODUCT_CATEGORY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_PRODUCT)
public class Product implements Serializable {

    private static final long serialVersionUID = 4336149754351482717L;

    @Id
    @Include
    @JsonProperty("code")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String nmProduct;

    @JsonProperty("description")
    private String dsProduct;

    @JsonProperty("price")
    private double vlPrice;

    @JsonProperty("image")
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = TB_PRODUCT_CATEGORY,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonProperty("categories")
    private Set<Category> categoryList = new HashSet<>();

    @JsonIgnore
    @JsonProperty("orders")
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> orderList = new HashSet<>();

    public Product(Long id, String nmProduct, String dsProduct, double vlPrice, String imgUrl) {
        super();
        this.id = id;
        this.nmProduct = nmProduct;
        this.dsProduct = dsProduct;
        this.vlPrice = vlPrice;
        this.imgUrl = imgUrl;
    }
}
