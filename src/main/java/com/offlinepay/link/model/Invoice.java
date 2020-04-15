package com.offlinepay.link.model;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class Invoice {

    @NotNull
    CurrencyCode currencyCode;

    @NotNull
    @Min(value = 0)
    Integer amountMajor;

    Integer amountMinor;
}
