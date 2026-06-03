package com.senprac.sto.repairorder.service;

import com.senprac.sto.repairorder.dto.RepairOrderDto;
import com.senprac.sto.repairorder.mapper.RepairOrderMapper;
import com.senprac.sto.repairorder.repository.RepairOrderRepository;
import com.senprac.sto.car.repository.CarRepository;
import com.senprac.sto.mechanic.repository.MechanicRepository;
import com.senprac.sto.entity.RepairOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairOrderService {

    private final RepairOrderRepository orderRepository;
    private final CarRepository carRepository;
    private final MechanicRepository mechanicRepository;
    private final RepairOrderMapper orderMapper;

    public RepairOrderDto createOrder(Long carId, String faultDescription) {
        return carRepository.findById(carId).map(car -> {
            RepairOrder order = RepairOrder.builder()
                    .receptionDate(LocalDate.now())
                    .faultDescription(faultDescription)
                    .status(RepairOrder.OrderStatus.ACCEPTED)
                    .car(car)
                    .build();
            return orderMapper.toDto(orderRepository.save(order));
        }).orElseThrow(() -> new RuntimeException("Автомобиль не найден"));
    }

    public RepairOrderDto assignMechanic(Long orderId, Long mechanicId) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        return mechanicRepository.findById(mechanicId).map(mechanic -> {
            order.setMechanic(mechanic);
            order.setStatus(RepairOrder.OrderStatus.IN_PROGRESS);
            return orderMapper.toDto(orderRepository.save(order));
        }).orElseThrow(() -> new RuntimeException("Механик не найден"));
    }

    public RepairOrderDto updateStatus(Long orderId, RepairOrder.OrderStatus newStatus) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        order.setStatus(newStatus);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public List<RepairOrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public List<RepairOrderDto> getOrdersByStatus(RepairOrder.OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public List<RepairOrderDto> getOrdersByCar(Long carId) {
        return orderRepository.findByCarId(carId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public List<RepairOrderDto> getOrdersByMechanic(Long mechanicId) {
        return orderRepository.findByMechanicId(mechanicId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}