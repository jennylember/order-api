package ru.lember.orderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lember.orderapi.controller.dto.CreateOrder;
import ru.lember.orderapi.controller.dto.OrderDTO;
import ru.lember.orderapi.controller.dto.UpdateOrder;
import ru.lember.orderapi.data.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final MyMetricsService myMetricsService;
    private final OrderIdGenerator idGenerator;

    @Autowired
    public OrderController(OrderService orderService, MyMetricsService myMetricsService, OrderIdGenerator idGenerator) {
        this.orderService = orderService;
        this.myMetricsService = myMetricsService;
        this.idGenerator = idGenerator;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrder request) {
        OrderDTO orderDTO = new OrderDTO(idGenerator.generateId(), request.getDescription());
        orderService.saveOrder(orderDTO);
        myMetricsService.incrementMyMetric();
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully orderId = " + orderDTO.getId());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable String orderId, @RequestBody UpdateOrder request) {
        OrderDTO orderDTO = new OrderDTO(orderId, request.getDescription());
        if (orderService.updateOrder(orderDTO)) {
            return ResponseEntity.ok("Order updated successfully orderId = " + orderDTO.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String orderId) {
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        if (orderDTO != null) {
            return ResponseEntity.ok(orderDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
        if (orderService.deleteById(orderId)) {
            return ResponseEntity.ok("Order deleted successfully orderId = " + orderId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}