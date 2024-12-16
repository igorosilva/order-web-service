package com.educandoweb.course.repository;

import com.educandoweb.course.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, GenericRepository<User> {
    boolean existsByNmUserOrDsEmail(String nmUser, String dsEmail);
    boolean existsByNmUser(String nmUser);
}
