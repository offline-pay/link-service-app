package com.offlinepay.link.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Prefix {
    MR("Mr"), MRS("Mrs"), MISS("Miss"), MS("Ms");

    private String name;
    Prefix(String name ) {
        this.name = name;
    }

    @JsonValue
    String getName(){
        return this.name;
    }
}
