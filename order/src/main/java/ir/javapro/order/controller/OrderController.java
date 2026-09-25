package ir.javapro.order.controller;

import ir.javapro.order.model.Order;
import ir.javapro.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @GetMapping("/get-all")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Order findById(@PathVariable Long id) {
        return orderService.findById(id);
    }
}