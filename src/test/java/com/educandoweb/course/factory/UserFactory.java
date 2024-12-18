package com.educandoweb.course.factory;

import com.educandoweb.course.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserFactory {

    private static LocalDateTime now = LocalDateTime.now();

    public static User createUser() {
        return User.builder()
                .id(1L)
                .nmUser("nmUser")
                .dsEmail("dsEmail")
                .nrPhone("nrPhone")
                .dsPassword("dsPassword")
                ._createdAt(now)
                ._updatedAt(now)
                .orderList(List.of())
                .build();
    }
}
