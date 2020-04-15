package com.offlinepay.link.converter;

import com.neovisionaries.i18n.CurrencyCode;
import com.offlinepay.link.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TypeConverterTest {

    @Test
    public void testOrderConverter() {

        Order model = new Order();
        model.setBookingDescription("booking description");
        model.setBookingReferencePrefix("Test merchant prefix");
        Customer customer = new Customer();
        customer.setEmail("merchant@m.com");
        customer.setPrefix(Prefix.MR);
        customer.setFirstName("Donald");
        customer.setFamilyName("Trump");
        model.setCustomer(customer);
        Invoice invoice = new Invoice();
        invoice.setCurrencyCode(CurrencyCode.USD);
        invoice.setAmountMajor(100);
        invoice.setAmountMinor(30);
        model.setInvoice(invoice);
        model.setLocale("en_US");
        LinkProperties linkProperties = new LinkProperties();
        linkProperties.setValidity(ValidityPeriod.EIGHT_HR);
        model.setLinkProperties(linkProperties);
        com.offlinepay.link.entity.LinkOrderEntity entity = TypeConverter.convert(model);

        assertEquals(entity.getAmountMajor(), 100);
        assertEquals(entity.getPrefix(), "MR");
        assertEquals(entity.getFirstName(), "Donald");
        assertEquals(entity.getLastName(), "Trump");
        assertTrue(entity.getBookingReference().contains("Test merchant prefix"));
        assertEquals(entity.getAmountMinor(), 30);
        assertEquals(entity.getCurrency(), "USD");
        // assertEquals(entity.getLinkValidity(), "");//TODO
        assertEquals(entity.getEmail(), "merchant@m.com");
        assertEquals(entity.getBookingDescription(), "booking description");
        assertEquals(entity.getLocale(), "en_US");

    }
}
