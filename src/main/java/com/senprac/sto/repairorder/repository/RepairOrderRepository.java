package com.senprac.sto.repairorder.repository;

import com.senprac.sto.entity.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    List<RepairOrder> findByStatus(RepairOrder.OrderStatus status);
    List<RepairOrder> findByCarId(Long carId);
    List<RepairOrder> findByMechanicId(Long mechanicId);
}