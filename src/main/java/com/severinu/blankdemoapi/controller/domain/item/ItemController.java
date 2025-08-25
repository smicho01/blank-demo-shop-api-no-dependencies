package com.severinu.blankdemoapi.controller.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @GetMapping
    public String getItem() {
        log.info("[GET] /item getItem");
        return "Item";
    }
}
