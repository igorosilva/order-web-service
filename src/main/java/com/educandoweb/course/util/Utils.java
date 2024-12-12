package com.educandoweb.course.util;

import com.educandoweb.course.controller.UserController;

public class Utils {

    public static final String className = UserController.class.getSimpleName();
    public static final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

    public static String logBuilder(String... args) {
        StringBuilder log = new StringBuilder();

        for (String arg : args) {

            log.append(arg).append(" ");
        }

        return log.toString().trim();
    }
}
