package com.offlinepay.link.entity;

import com.googlecode.jmapper.annotations.JMapConversion;
import com.neovisionaries.i18n.CurrencyCode;
import com.offlinepay.link.model.LinkProperties;
import com.offlinepay.link.model.Prefix;
import com.offlinepay.link.model.ValidityPeriod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "LinkOrder")
@IdClass(LinkOrderEntityKey.class)
@Getter
@Setter
@ToString
public class LinkOrderEntity {
    @Id
    Integer merchantId;

    @Id
    @GeneratedValue //auto
    Integer orderId;

    String prefix;
    String firstName;
    String lastName;
    String email;
    String bookingReference;
    String bookingDescription;
    Integer amountMajor;
    Integer amountMinor;
    String currency;
    String locale;
    LocalDateTime linkValidity;

    @JMapConversion(from = {"currencyCode"}, to = {"currency"})
    public String convertCurrency(CurrencyCode currencyCode) {
        return currencyCode.name();
    }


    @JMapConversion(from = {"bookingReferencePrefix"}, to = {"bookingReference"})
    public String conversionPrefix(String bookingPrefix) {
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
            case ONE_D:
            default:
                expiryDateTime = dateTime.plusDays(1);
                break;
        }
        return expiryDateTime;
    }

}
