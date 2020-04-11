package com.offlinepay.link.service;

import lombok.Data;

@Data
public class LinkOrder {
    Integer orderId;
    Integer merchantId;
    boolean isValid;
}
