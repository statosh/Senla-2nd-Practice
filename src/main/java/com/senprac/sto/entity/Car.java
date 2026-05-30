package com.senprac.sto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plateNumber;
    private String brand;
    private String model;

    @Column(name = "production_year")
    private int productionYear;

    private String vin;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<RepairOrder> repairOrders;

    public Car() {}

    public Car(String plateNumber, String brand, String model, int productionYear, String vin, Client client) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.vin = vin;
        this.client = client;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getProductionYear() { return productionYear; }
    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<RepairOrder> getRepairOrders() { return repairOrders; }
    public void setRepairOrders(List<RepairOrder> repairOrders) { this.repairOrders = repairOrders; }
}