package com.senprac.sto.repairorder.service;

import com.senprac.sto.repairorder.repository.RepairOrderRepository;
import com.senprac.sto.car.repository.CarRepository;
import com.senprac.sto.mechanic.repository.MechanicRepository;
import com.senprac.sto.entity.RepairOrder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RepairOrderService {

    private final RepairOrderRepository orderRepository;
    private final CarRepository carRepository;
    private final MechanicRepository mechanicRepository;

    public RepairOrderService(RepairOrderRepository orderRepository,
                              CarRepository carRepository,
                              MechanicRepository mechanicRepository) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
        this.mechanicRepository = mechanicRepository;
    }

    public RepairOrder createOrder(Long carId, String faultDescription) {
        return carRepository.findById(carId).map(car -> {
            RepairOrder order = new RepairOrder(
                    LocalDate.now(),
                    faultDescription,
                    RepairOrder.OrderStatus.ACCEPTED,
                    car
            );
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Автомобиль не найден"));
    }

    public RepairOrder assignMechanic(Long orderId, Long mechanicId) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        return mechanicRepository.findById(mechanicId).map(mechanic -> {
            order.setMechanic(mechanic);
            order.setStatus(RepairOrder.OrderStatus.IN_PROGRESS);
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Механик не найден"));
    }

    public RepairOrder updateStatus(Long orderId, RepairOrder.OrderStatus newStatus) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public List<RepairOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<RepairOrder> getOrdersByStatus(RepairOrder.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<RepairOrder> getOrdersByCar(Long carId) {
        return orderRepository.findByCarId(carId);
    }

    public List<RepairOrder> getOrdersByMechanic(Long mechanicId) {
        return orderRepository.findByMechanicId(mechanicId);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}