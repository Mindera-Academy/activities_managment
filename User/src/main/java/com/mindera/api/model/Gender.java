package com.mindera.api.model;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.isNull;

public enum Gender {
    M,
    F,
    O;

    public static Gender fromValue(String value){
        if (isNull(value) || value.isBlank()){
            return null;
        }
        if (Arrays.stream(Gender.values()).map(Enum::name).toList().contains(value)){
            return Gender.valueOf(value);
        }
        return null;
    }
}
