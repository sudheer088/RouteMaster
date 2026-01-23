package com.next.routeMaster.dto;

public class VehicleDto {
    private Long vehicleId;
    private String numberplate;
    private int capaity;
    private String driverName;
    private String routeName;
    private Long routeId;

    public VehicleDto(Long vehicleId, String numberplate, int capaity, String driverName, String routeName, Long routeId) {
        this.vehicleId = vehicleId;
        this.numberplate = numberplate;
        this.capaity = capaity;
        this.driverName = driverName;
        this.routeName = routeName;
        this.routeId = routeId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public int getCapaity() {
        return capaity;
    }

    public void setCapaity(int capaity) {
        this.capaity = capaity;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}
