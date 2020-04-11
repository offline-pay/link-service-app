package com.offlinepay.link.entity;

import com.googlecode.jmapper.annotations.JMapConversion;
import com.offlinepay.link.model.LinkProperties;
import com.offlinepay.link.model.Prefix;
import com.offlinepay.link.model.ValidityPeriod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@IdClass(OrderId.class)
@Getter
@Setter
@ToString
public class Order {
    @Id
    Integer merchantId;
    @Id @GeneratedValue //auto
    Integer orderId;

    String prefix;
    String name;
    String lastName;
    String bookingReference;
    Integer amountMajor;
    Integer amountMinor;
    String currency;
    LocalDateTime linkValidity;
    String email;
    String bookingDescription;
    String language;

    @JMapConversion(from = {"bookingReferencePrefix"}, to = {"bookingReference"})
    public String conversion(String bookingPrefix) {
        return bookingPrefix + UUID.randomUUID().toString();
    }

    @JMapConversion(from = {"prefix"}, to = {"prefix"})
    public String conversion(Prefix prefix) {
        return prefix.toString();
    }

    /**
     * The default expiry time is set to 1 day.
     * @param linkProperties
     * @return
     */
    @JMapConversion(from = {"linkProperties"}, to = {"linkValidity"})
    public LocalDateTime linkValidityConverter(LinkProperties linkProperties) {
        ValidityPeriod period = linkProperties.getValidity();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime;
        switch (period) {
            case ONE_HR:
                expiryDateTime = dateTime.plusHours(1);
                break;
            case TWO_HR:
                expiryDateTime = dateTime.plusHours(2);
                break;
            case FOUR_HR:
                expiryDateTime = dateTime.plusHours(4);
                break;
            case EIGHT_HR:
                expiryDateTime = dateTime.plusHours(8);
                break;
            case SIXTEEN_HR:
                expiryDateTime = dateTime.plusHours(16);
                break;
            case ONE_D:
                expiryDateTime = dateTime.plusDays(1);
                break;
            case TWO_D:
                expiryDateTime = dateTime.plusDays(2);
                break;
            case THREE_D:
                expiryDateTime = dateTime.plusDays(3);
                break;
            case ONE_W:
                expiryDateTime = dateTime.plusDays(7);
                break;
            case TWO_W:
                expiryDateTime = dateTime.plusDays(14);
                break;
            case FOUR_W:
                expiryDateTime = dateTime.plusDays(28);
                break;
            case CUSTOM:
                expiryDateTime = linkProperties.getValidUntil();
                break;
            default:
                expiryDateTime = dateTime.plusDays(1);
                break;
        }
        return expiryDateTime;
    }

}
