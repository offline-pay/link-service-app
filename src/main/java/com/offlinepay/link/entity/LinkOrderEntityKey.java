package com.offlinepay.link.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class LinkOrderEntityKey implements Serializable {
    private Integer merchantId;
    private Integer orderId;

}
