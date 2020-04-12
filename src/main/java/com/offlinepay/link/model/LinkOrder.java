package com.offlinepay.link.model;

import lombok.Data;

@Data
public class LinkOrder {

    Integer orderId;

    Integer merchantId;

    boolean isValid;
}
