package com.offlinepay.link.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ValidityPeriod {

    ONE_HR("1h"), TWO_HR("2h"), FOUR_HR("4h"), EIGHT_HR("8h"), SIXTEEN_HR("16h"), ONE_D("1d"), TWO_D("2d"), THREE_D("3d"), ONE_W("1w"), TWO_W("2w"), FOUR_W(("4w")), CUSTOM("#");

    private String shortName;
    ValidityPeriod(String shortName) {
        this.shortName = shortName;
    }
    @JsonValue
    String getName() {
        return this.shortName;
    }

}
