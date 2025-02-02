package com.educandoweb.course.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.educandoweb.course.util.Constants.TB_USER;
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
@Table(name = TB_USER)
public class User implements Serializable {

    private static final long serialVersionUID = 8067165714208918931L;

    @Id
    @Include
    @JsonProperty("code")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JsonProperty("user")
    private String nmUser;

    @JsonProperty("email")
    private String dsEmail;

    @JsonProperty("phone")
    private String nrPhone;

    @JsonProperty("password")
    private String dsPassword;

    @CreationTimestamp
    @JsonProperty("createdAt")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime _createdAt;

    @UpdateTimestamp
    @JsonProperty("updatedAt")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime _updatedAt;

    @OneToMany
    @JsonProperty("orders")
    private Set<Order> orderList = new HashSet<>();

    public User(Long id, String nmUser, String dsEmail, String nrPhone, String dsPassword) {
        super();
        this.id = id;
        this.nmUser = nmUser;
        this.dsEmail = dsEmail;
        this.nrPhone = nrPhone;
        this.dsPassword = dsPassword;
    }

    public User(Long id, String nmUser, String dsEmail, String nrPhone, String dsPassword, LocalDateTime _createdAt, LocalDateTime _updatedAt) {
        this(id, nmUser, dsEmail, nrPhone, dsEmail);
        this._createdAt = _createdAt;
        this._updatedAt = _updatedAt;
    }
}
