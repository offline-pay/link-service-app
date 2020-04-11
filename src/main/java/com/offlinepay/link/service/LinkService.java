package com.offlinepay.link.service;

import com.offlinepay.link.converter.TypeConverter;
import com.offlinepay.link.crypto.HashUtil;
import com.offlinepay.link.entity.LinkValidity;
import com.offlinepay.link.model.Order;
import com.offlinepay.link.repository.LinkValidityRepository;
import com.offlinepay.link.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    LinkValidityRepository linkValidityRepository;

    @Autowired
    ChannelService channelService;

    @Value("link-base")
    String linkBaseUrl;

    /*
        This method does,
        - Save the order in db
        - generate a unique number which is mapped to saved order
        - create a link with unique number
     */
    public String createLink(Order order) {
        com.offlinepay.link.entity.Order persistedOrder = orderRepository.save(TypeConverter.convert(order));
        LinkValidity validity = LinkValidity.builder().merchantId(persistedOrder.getMerchantId()).orderId(persistedOrder.getOrderId()).linkHash(HashUtil.hash(persistedOrder.getMerchantId() + "" + persistedOrder.getOrderId())).build();
        linkValidityRepository.save(validity);
        return linkBaseUrl + "/links/" + validity.getLinkHash();
    }

    public LinkOrder validateLink(String hash) {
        Optional<LinkValidity> linkValidity = linkValidityRepository.findById(hash);
        LinkOrder linkOrder = new LinkOrder();
        if (linkValidity.isPresent()) {
            LinkValidity validity = linkValidity.get();
            boolean isValid = validity.getValidTill().isAfter(LocalDateTime.now());
            linkOrder.setValid(isValid);
            linkOrder.setMerchantId(validity.getMerchantId());
            linkOrder.setOrderId(validity.getOrderId());
        }
        return linkOrder;
    }
}
