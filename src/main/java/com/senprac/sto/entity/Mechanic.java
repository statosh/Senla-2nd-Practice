package com.senprac.sto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mechanics")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String specialization;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "mechanic")
    private List<RepairOrder> repairOrders;

    public Mechanic() {}

    public Mechanic(String fullName, String specialization, String phone) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<RepairOrder> getRepairOrders() { return repairOrders; }
    public void setRepairOrders(List<RepairOrder> repairOrders) { this.repairOrders = repairOrders; }
}