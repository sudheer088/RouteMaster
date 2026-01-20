package com.next.routeMaster.service;

import com.next.routeMaster.entity.Route;
import com.next.routeMaster.entity.Vehicle;
import com.next.routeMaster.repository.RouteRepo;
import com.next.routeMaster.repository.VehicleRepo;

import java.util.List;

public class VehicleService {
    private VehicleRepo vehicleRepo;
    private RouteRepo routeRepo;
    public VehicleService(VehicleRepo vehicleRepo,RouteRepo routeRepo){
        this.vehicleRepo=vehicleRepo;
        this.routeRepo=routeRepo;
    }
    public Vehicle addVehicle(Vehicle vehicle,Long routeId){
        Route route= routeRepo.findById(routeId).orElseThrow(()->new RuntimeException("Route not found"));
        vehicle.setRoute(route);
        return vehicleRepo.save(vehicle);
    }
    public List<Vehicle> getVehiclesByRoute(Long routeId){
        return vehicleRepo.findByRouteId(routeId);
    }

}
