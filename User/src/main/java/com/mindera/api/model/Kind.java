package com.mindera.api.model;

import java.util.Arrays;

import static java.util.Objects.isNull;

public enum Kind {
    Student,
    Parent,
    Admin;

    public static Kind fromValue(String value){
        if (isNull(value) || value.isBlank()){
            return null;
        }
        if (Arrays.stream(Kind.values()).map(Enum::name).toList().contains(value)){
            return Kind.valueOf(value);
        }
        return null;
    }
}
