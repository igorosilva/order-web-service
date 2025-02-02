package com.educandoweb.course.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Objects;

import static com.educandoweb.course.util.Constants.TB_CATEGORY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Entity
@Builder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_CATEGORY)
public class Category implements Serializable {

    private static final long serialVersionUID = 3761016518219785510L;

    @Id
    @Include
    @JsonProperty("code")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String nmCategory;

    public Category(Long id, String nmCategory) {
        this.id = id;
        this.nmCategory = nmCategory;
    }

    public Long getId() {
        return id;
    }

    public String getNmCategory() {
        return nmCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(nmCategory, category.nmCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nmCategory);
    }
}
