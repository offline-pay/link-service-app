package com.offlinepay.link.controller;

import com.offlinepay.link.model.Order;
import com.offlinepay.link.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @PostMapping
    public void create(@RequestBody Order order) {
       // linkService.
    }
}
