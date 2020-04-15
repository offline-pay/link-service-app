package com.offlinepay.link.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class Merchant {
    //@Size(min = 0, max = 999999)
    Long merchantId;
}
