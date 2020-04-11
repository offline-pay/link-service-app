package com.offlinepay.link.entity;

import javax.persistence.EmbeddedId;
import java.io.Serializable;

public class OrderId implements Serializable {
    private Integer merchantId;
    private Integer orderId;

}
