package com.educandoweb.course.repository;

import com.educandoweb.course.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

    boolean existsByNmUserOrDsEmail(String nmUser, String dsEmail);
}
