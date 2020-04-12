package com.offlinepay.link.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "LinkValidity")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkValidityEntity {
    @Id
    String linkHash;

    Integer orderId;

    Integer merchantId;

    LocalDateTime validTill;

    String salt;
}
