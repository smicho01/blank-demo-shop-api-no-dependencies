package com.severinu.blankdemoapi.controller.domain.order;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private List<Order> orders = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setId(nextId++);
        orders.add(order);
        return order;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrder = orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();

        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setDescription(updatedOrder.getDescription());
            order.setAmount(updatedOrder.getAmount());
            return order;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        boolean removed = orders.removeIf(order -> order.getId().equals(id));
        return removed ? "Order deleted successfully" : "Order not found";
    }


}