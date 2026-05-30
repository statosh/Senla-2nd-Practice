package com.senprac.sto.repairorder.controller;

import com.senprac.sto.repairorder.service.RepairOrderService;
import com.senprac.sto.entity.RepairOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class RepairOrderController {

    private final RepairOrderService orderService;

    public RepairOrderController(RepairOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/car/{carId}")
    public ResponseEntity<RepairOrder> createOrder(@PathVariable Long carId,
                                                    @RequestParam String faultDescription) {
        return ResponseEntity.ok(orderService.createOrder(carId, faultDescription));
    }

    @PutMapping("/{orderId}/assign/{mechanicId}")
    public ResponseEntity<RepairOrder> assignMechanic(@PathVariable Long orderId,
                                                       @PathVariable Long mechanicId) {
        return ResponseEntity.ok(orderService.assignMechanic(orderId, mechanicId));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<RepairOrder> updateStatus(@PathVariable Long orderId,
                                                     @RequestParam RepairOrder.OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(orderId, status));
    }

    @GetMapping
    public ResponseEntity<List<RepairOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<RepairOrder>> getOrdersByCar(@PathVariable Long carId) {
        return ResponseEntity.ok(orderService.getOrdersByCar(carId));
    }

    @GetMapping("/mechanic/{mechanicId}")
    public ResponseEntity<List<RepairOrder>> getOrdersByMechanic(@PathVariable Long mechanicId) {
        return ResponseEntity.ok(orderService.getOrdersByMechanic(mechanicId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}