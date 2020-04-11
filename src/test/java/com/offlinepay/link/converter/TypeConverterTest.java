package com.offlinepay.link.converter;

import com.offlinepay.link.model.*;
import org.junit.jupiter.api.Test;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class TypeConverterTest {

    @Test
    public void testOrderConverter() {

        Order model = new Order();
        model.setBookingDescription("booking description");
        model.setBookingReferencePrefix("Test merchant prefix");
        Customer customer = new Customer();
        customer.setEmail("merchant@m.com");
        customer.setPrefix(Prefix.MR);
        customer.setName("Donald");
        customer.setFamilyName("Trump");
        model.setCustomer(customer);
        Invoice invoice = new Invoice();
        invoice.setCurrencyCode("USD");
        invoice.setAmountMajor(100);
        invoice.setAmountMinor(30);
        model.setInvoice(invoice);
        model.setLanguage("Us_en");
        LinkProperties linkProperties = new LinkProperties();
        linkProperties.setValidity(ValidityPeriod.EIGHT_HR);
        model.setLinkProperties(linkProperties);
        com.offlinepay.link.entity.Order entity = TypeConverter.convert(model);

        assertEquals(entity.getAmountMajor(), 100);
        assertEquals(entity.getPrefix(), "MR");
        assertEquals(entity.getName(), "Donald");
        assertEquals(entity.getLastName(), "Trump");
        assertTrue(entity.getBookingReference().contains("Test merchant prefix"));
        assertEquals(entity.getAmountMinor(), 30);
        assertEquals(entity.getCurrency(), "USD");
        // assertEquals(entity.getLinkValidity(), "");//TODO
        assertEquals(entity.getEmail(), "merchant@m.com");
        assertEquals(entity.getBookingDescription(), "booking description");
        assertEquals(entity.getLanguage(), "Us_en");

    }
}
