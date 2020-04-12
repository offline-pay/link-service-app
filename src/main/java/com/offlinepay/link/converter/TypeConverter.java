package com.offlinepay.link.converter;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.offlinepay.link.entity.LinkOrderEntity;
import com.offlinepay.link.model.Order;

import static com.googlecode.jmapper.api.JMapperAPI.attribute;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;

public class TypeConverter {

    public static LinkOrderEntity convert(Order model) {

        JMapperAPI jmapperApi = new JMapperAPI().add(mappedClass(LinkOrderEntity.class)
                        .add(attribute("prefix").value("${customer.prefix}"))
                        .add(attribute("firstName").value("${customer.firstName}"))
                        .add(attribute("lastName").value("${customer.familyName}"))
                        .add(attribute("email").value("${customer.email}"))
                        .add(attribute("amountMajor").value("${invoice.amountMajor}"))
                        .add(attribute("amountMinor").value("${invoice.amountMinor}"))
                        .add(attribute("currency").value("${invoice.currencyCode}"))
                        .add(attribute("linkValidity").value("linkProperties"))
                        .add(attribute("bookingDescription").value("bookingDescription"))
                        .add(attribute("bookingReference").value("bookingReferencePrefix"))
                        .add(attribute("locale").value("locale"))
                );

        JMapper<LinkOrderEntity, Order> userMapper = new JMapper<>(LinkOrderEntity.class, Order.class, jmapperApi);

        return userMapper.getDestination(model);
    }

}
