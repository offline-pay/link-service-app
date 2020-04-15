package com.offlinepay.link.controller;

import com.offlinepay.link.model.Link;
import com.offlinepay.link.model.Order;
import com.offlinepay.link.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/link/v1/")
@Tag(name = "Link", description = "The Link API")
public class LinkController {

    final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    @Operation(summary = "Create a new link", description = "Create a link for given order", responses = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public Link create(@RequestBody Order order) {
        return linkService.createLink(order); //TODO exception handler
    }

    //validate link

}
