package com.offlinepay.link.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @Builder @NoArgsConstructor
public class LinkValidity {
    @Id
    String linkHash;

    Integer orderId;
    Integer merchantId;

    LocalDateTime validTill;
    String salt;

}
