package com.halyk.bookstore.data.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    CREATED("created"),
    PROCESS("process"),
    EXECUTED("executed"),
    CANCELED("canceled");

    private String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

}
