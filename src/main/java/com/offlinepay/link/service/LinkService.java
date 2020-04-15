package com.offlinepay.link.service;

import com.offlinepay.link.converter.TypeConverter;
import com.offlinepay.link.crypto.HashUtil;
import com.offlinepay.link.entity.LinkOrderEntity;
import com.offlinepay.link.entity.LinkValidityEntity;
import com.offlinepay.link.model.Link;
import com.offlinepay.link.model.LinkOrder;
import com.offlinepay.link.model.Order;
import com.offlinepay.link.repository.LinkValidityRepository;
import com.offlinepay.link.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LinkService {

    final OrderRepository orderRepository;

    final LinkValidityRepository linkValidityRepository;

    final ChannelService channelService;

    @Value("${link.base}")
    String linkBaseUrl;

    public LinkService(OrderRepository orderRepository, LinkValidityRepository linkValidityRepository,
                       ChannelService channelService) {
        this.orderRepository = orderRepository;
        this.linkValidityRepository = linkValidityRepository;
        this.channelService = channelService;
    }

    /**
     *   This method does,
     *  - Save the order in db
     *  - generate a unique number which is mapped to saved order
     *  - create a link with unique number
     */
    public Link createLink(Order order) {

        LinkOrderEntity persistedOrder = orderRepository.save(TypeConverter.convert(order));

        LinkValidityEntity validity = LinkValidityEntity.builder().
                merchantId(persistedOrder.getMerchantId()).
                orderId(persistedOrder.getOrderId()).
                validTill(persistedOrder.getLinkValidity()).
                linkHash(HashUtil.hash(persistedOrder.getMerchantId() + "" + persistedOrder.getOrderId()))
                .build();

        linkValidityRepository.save(validity);

        Link link = new Link();
        link.setUrl(linkBaseUrl + "/payment-links/" + validity.getLinkHash());
        return link;
    }


    /**
     *
     * @param hash
     * @return
     */
    public LinkOrder validateLink(String hash) {

        Optional<LinkValidityEntity> linkValidity = linkValidityRepository.findById(hash);

        LinkOrder linkOrder = new LinkOrder();

        if (linkValidity.isPresent()) {
            LinkValidityEntity validity = linkValidity.get();
            boolean isValid = validity.getValidTill().isAfter(LocalDateTime.now());
            linkOrder.setValid(isValid);
            linkOrder.setMerchantId(validity.getMerchantId());
            linkOrder.setOrderId(validity.getOrderId());
        }

        return linkOrder;
    }

}
