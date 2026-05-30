package com.senprac.sto.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repair_orders")
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate receptionDate;
    private String faultDescription;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    public enum OrderStatus {
        ACCEPTED, IN_PROGRESS, WAITING_PARTS, READY, ISSUED
    }

    public RepairOrder() {}

    public RepairOrder(LocalDate receptionDate, String faultDescription, OrderStatus status, Car car) {
        this.receptionDate = receptionDate;
        this.faultDescription = faultDescription;
        this.status = status;
        this.car = car;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getReceptionDate() { return receptionDate; }
    public void setReceptionDate(LocalDate receptionDate) { this.receptionDate = receptionDate; }

    public String getFaultDescription() { return faultDescription; }
    public void setFaultDescription(String faultDescription) { this.faultDescription = faultDescription; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public Mechanic getMechanic() { return mechanic; }
    public void setMechanic(Mechanic mechanic) { this.mechanic = mechanic; }
}