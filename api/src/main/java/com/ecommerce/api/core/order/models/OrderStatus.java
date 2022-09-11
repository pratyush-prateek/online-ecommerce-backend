package com.ecommerce.api.core.order.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum OrderStatus {
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled");

    private String value;
    
    OrderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static OrderStatus fromValue(String value) {
        return Stream.of(OrderStatus.values())
                .filter(status -> value.equals(status.getValue()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
