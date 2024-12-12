package com.educandoweb.course.util;

import com.educandoweb.course.controller.UserController;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class Utils {

    public static final String className = UserController.class.getSimpleName();

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String logBuilder(String... args) {
        return stream(args).collect(joining(" "));
    }

    public static Class<?> getObjectClass(Object object) {
        return object.getClass();
    }
}
