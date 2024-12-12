package com.educandoweb.course.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAITING_PAYMENT(0, "Waiting Payment"),
    PAID(1, "Paid"),
    SHIPPED(2, "Shipeed"),
    DELIVERED(3, "Delivered"),
    CANCELED(4, "Canceled");

    private int code;
    private String descriptions;

    public static OrderStatus valueOf(int code) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if(orderStatus.getCode() == code) {
                return orderStatus;
            }
        }

        return null;
    }
}
