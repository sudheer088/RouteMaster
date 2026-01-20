package com.next.routeMaster.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Vehicle {
    @Id
    @GeneratedValue
    private Long Vehicle_id;
    @Column(nullable = false,unique = true)
    private String vehicleNumber;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private String DriverName;
    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

    public Vehicle() {
    }

    public Vehicle(String vehicleNumber, int capacity, String driverName, Route route) {
        this.vehicleNumber = vehicleNumber;
        this.capacity = capacity;
         this.DriverName = driverName;
        this.route = route;
    }

    public Long getVehicle_id() {
        return Vehicle_id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
