package com.next.routeMaster.controller;

import com.next.routeMaster.entity.Vehicle;
import com.next.routeMaster.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;
    }
    @PostMapping("/route/{routeId}")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle,
                              @PathVariable Long routeId){
        return vehicleService.addVehicle(vehicle,routeId);
    }
    @GetMapping("/route/{routeId}")
    public List<Vehicle> getVehiclesByROUTE(@PathVariable Long routeId){
        return vehicleService.getVehiclesByRoute(routeId);
    }
}
