package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN, CLIENT;

    @JsonCreator
    public static Role fromString(String value) {
        if (value != null) {
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return role;
                }
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }

    @JsonValue
    public String toValue() {
        return name();
    }

}