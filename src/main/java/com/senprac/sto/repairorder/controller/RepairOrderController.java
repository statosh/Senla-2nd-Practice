package com.senprac.sto.repairorder.controller;

import com.senprac.sto.repairorder.dto.RepairOrderDto;
import com.senprac.sto.repairorder.service.RepairOrderService;
import com.senprac.sto.entity.RepairOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class RepairOrderController {

    private final RepairOrderService orderService;

    @PostMapping("/car/{carId}")
    public ResponseEntity<RepairOrderDto> createOrder(@PathVariable Long carId,
                                                       @RequestParam String faultDescription) {
        return ResponseEntity.ok(orderService.createOrder(carId, faultDescription));
    }

    @PutMapping("/{orderId}/assign/{mechanicId}")
    public ResponseEntity<RepairOrderDto> assignMechanic(@PathVariable Long orderId,
                                                          @PathVariable Long mechanicId) {
        return ResponseEntity.ok(orderService.assignMechanic(orderId, mechanicId));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<RepairOrderDto> updateStatus(@PathVariable Long orderId,
                                                        @RequestParam RepairOrder.OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(orderId, status));
    }

    @GetMapping
    public ResponseEntity<List<RepairOrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<RepairOrderDto>> getOrdersByCar(@PathVariable Long carId) {
        return ResponseEntity.ok(orderService.getOrdersByCar(carId));
    }

    @GetMapping("/mechanic/{mechanicId}")
    public ResponseEntity<List<RepairOrderDto>> getOrdersByMechanic(@PathVariable Long mechanicId) {
        return ResponseEntity.ok(orderService.getOrdersByMechanic(mechanicId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}