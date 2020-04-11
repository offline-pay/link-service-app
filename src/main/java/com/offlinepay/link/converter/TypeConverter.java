package com.offlinepay.link.converter;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.offlinepay.link.entity.Order;

import static com.googlecode.jmapper.api.JMapperAPI.attribute;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;

public class TypeConverter {
    public static Order convert(com.offlinepay.link.model.Order model) {
        JMapperAPI jmapperApi = new JMapperAPI()
                .add(mappedClass(Order.class)
                        .add(attribute("prefix").value("${customer.prefix}"))
                        .add(attribute("name").value("${customer.name}"))
                        .add(attribute("lastName").value("${customer.familyName}"))
                        .add(attribute("email").value("${customer.email}"))
                        .add(attribute("amountMajor").value("${invoice.amountMajor}"))
                        .add(attribute("amountMinor").value("${invoice.amountMinor}"))
                        .add(attribute("currency").value("${invoice.currencyCode}"))
                        .add(attribute("linkValidity").value("linkProperties"))
                        .add(attribute("bookingDescription").value("bookingDescription"))
                        .add(attribute("bookingReference").value("bookingReferencePrefix"))
                        .add(attribute("language").value("language"))
                );
        JMapper<Order, com.offlinepay.link.model.Order> userMapper = new JMapper<>
                (Order.class, com.offlinepay.link.model.Order.class, jmapperApi);
        return userMapper.getDestination(model);
    }

}
