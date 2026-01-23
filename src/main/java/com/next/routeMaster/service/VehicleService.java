package com.next.routeMaster.service;

import com.next.routeMaster.dto.VehicleDto;
import com.next.routeMaster.entity.Route;
import com.next.routeMaster.entity.Vehicle;
import com.next.routeMaster.repository.RouteRepo;
import com.next.routeMaster.repository.VehicleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
    public List<VehicleDto> getVehiclesByRoute(Long routeId) {

        List<Vehicle> vehicles = vehicleRepo.findByRouteId(routeId);

        return vehicles.stream()
                .map(v -> new VehicleDto(
                        v.getVehicle_id(),
                        v.getVehicleNumber(),
                        v.getCapacity(),
                        v.getDriverName(),
                        v.getRoute().getRoute_name(),
                        v.getRoute().getId()
                ))
                .toList();
    }

    public List<VehicleDto> getAllVehicles() {

        return vehicleRepo.findAll()
                .stream()
                .map(v -> {
                    Route route = v.getRoute();
                    return new VehicleDto(
                            v.getVehicle_id(),
                            v.getVehicleNumber(),
                            v.getCapacity(),
                            v.getDriverName(),
                            route != null ? route.getRoute_name() : null,
                            route != null ? route.getId() : null
                    );
                })
                .toList();
    }


    public VehicleDto getVehicleReportById(Long id) {

        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        Route route = vehicle.getRoute();

        return new VehicleDto(
                vehicle.getVehicle_id(),
                vehicle.getVehicleNumber(),
                vehicle.getCapacity(),
                vehicle.getDriverName(),
                route != null ? route.getRoute_name() : null,
                route != null ? route.getId() : null
        );
    }

}
