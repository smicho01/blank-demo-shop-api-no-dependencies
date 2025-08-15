package com.severinu.blankdemoapi.controller.domain.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    void getCustomer() {
        CustomerService customerService = new CustomerService();
        assertEquals("Customer", customerService.getCustomer());
    }

}