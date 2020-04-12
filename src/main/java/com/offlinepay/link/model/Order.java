package com.offlinepay.link.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    @NotNull
    String bookingReferencePrefix;

    @NotNull
    String bookingDescription;

    @NotNull
    Customer customer;

    @NotNull
    LinkProperties linkProperties;

    @NotNull
    String locale;

    @NotNull
    Invoice invoice;
}
