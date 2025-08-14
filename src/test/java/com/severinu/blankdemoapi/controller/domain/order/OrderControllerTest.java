package com.severinu.blankdemoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.severinu.blankdemoapi.controller.domain.order.Order;
import com.severinu.blankdemoapi.controller.domain.order.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnEmptyListWhenNoOrders() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {
        Order order = new Order("Test Order", 100.0);
        String orderJson = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Test Order"))
                .andExpect(jsonPath("$.amount").value(100.0));
    }


    @Test
    void shouldReturnNullForNonExistentOrder() throws Exception {
        mockMvc.perform(get("/order/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldUpdateOrderSuccessfully() throws Exception {
        // First create an order
        Order order = new Order("Original Order", 100.0);
        String orderJson = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson));

        // Then update it
        Order updatedOrder = new Order("Updated Order", 200.0);
        String updatedOrderJson = objectMapper.writeValueAsString(updatedOrder);

        mockMvc.perform(put("/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedOrderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Updated Order"))
                .andExpect(jsonPath("$.amount").value(200.0));
    }

    @Test
    void shouldReturnNullWhenUpdatingNonExistentOrder() throws Exception {
        Order order = new Order("Test Order", 100.0);
        String orderJson = objectMapper.writeValueAsString(order);

        mockMvc.perform(put("/order/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldDeleteOrderSuccessfully() throws Exception {
        // First create an order
        Order order = new Order("Test Order", 100.0);
        String orderJson = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson));

        // Then delete it
        mockMvc.perform(delete("/order/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order deleted successfully"));
    }

    @Test
    void shouldReturnNotFoundWhenDeletingNonExistentOrder() throws Exception {
        mockMvc.perform(delete("/order/999"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order not found"));
    }
}