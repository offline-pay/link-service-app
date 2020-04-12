package com.offlinepay.link.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Invoice {

    String currencyCode;

    Integer amountMajor;

    Integer amountMinor;
}
