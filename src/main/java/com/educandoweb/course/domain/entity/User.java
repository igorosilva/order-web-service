package com.educandoweb.course.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.educandoweb.course.util.Constants.TB_USER;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Entity
@Builder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(NON_NULL)
@Table(name = TB_USER)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public User(Long id, String nmUser, String dsEmail, String nrPhone, String dsPassword, LocalDateTime _createdAt, LocalDateTime _updatedAt) {
        this.id = id;
        this.nmUser = nmUser;
        this.dsEmail = dsEmail;
        this.nrPhone = nrPhone;
        this.dsPassword = dsPassword;
        this._createdAt = _createdAt;
        this._updatedAt = _updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getNmUser() {
        return nmUser;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public String getNrPhone() {
        return nrPhone;
    }

    public String getDsPassword() {
        return dsPassword;
    }

    public LocalDateTime getCreatedAt() {
        return _createdAt;
    }

    public void setCreatedAt(LocalDateTime _createdAt) {
        this._createdAt = _createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return _updatedAt;
    }

    public void setUpdatedAt(LocalDateTime _updatedAt) {
        this._updatedAt = _updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nmUser, user.nmUser) && Objects.equals(dsEmail, user.dsEmail) && Objects.equals(nrPhone, user.nrPhone) && Objects.equals(dsPassword, user.dsPassword) && Objects.equals(_createdAt, user._createdAt) && Objects.equals(_updatedAt, user._updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nmUser, dsEmail, nrPhone, dsPassword, _createdAt, _updatedAt);
    }
}
