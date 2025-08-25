package com.severinu.blankdemoapi.controller.domain.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Value("${serviceX.enabled}")
    private boolean serviceXEnabled;

    @GetMapping
    public String getCustomer() {
        log.info("[GET] /customer getCustomer");
        if(serviceXEnabled) {
            System.out.println("serviceXEnabled");
        } else {
            System.out.println("serviceXDisabled");
        }
        return "Customer";
    }
}