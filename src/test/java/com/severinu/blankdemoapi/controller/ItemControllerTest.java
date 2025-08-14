package com.severinu.blankdemoapi.controller;

import com.severinu.blankdemoapi.controller.domain.item.ItemController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnItemWhenGetItemCalled() throws Exception {
        mockMvc.perform(get("/item"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item"));
    }

    @Test
    void shouldReturnTextPlainContentType() throws Exception {
        mockMvc.perform(get("/item"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void shouldReturn404ForInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/item/invalid"))
                .andExpect(status().isNotFound());
    }
}