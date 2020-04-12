package com.offlinepay.link.controller;

import com.offlinepay.link.model.Order;
import com.offlinepay.link.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/link/v1/")
public class LinkController {

    final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public void create(@RequestBody Order order) {
       linkService.createLink(order);
    }

}
