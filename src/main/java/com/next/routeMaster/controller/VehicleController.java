package com.next.routeMaster.controller;

import com.next.routeMaster.dto.VehicleDto;
import com.next.routeMaster.entity.Vehicle;
import com.next.routeMaster.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<VehicleDto> getVehiclesByROUTE(@PathVariable Long routeId){
        return vehicleService.getVehiclesByRoute(routeId);
    }
    @GetMapping("/all")
    public ResponseEntity<List<VehicleDto>> getallVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
    @GetMapping("/vehicleId/{vehicleId}")
    public ResponseEntity<?> getVehicleReportById(@PathVariable Long vehicleId) {
        try {
            VehicleDto dto = vehicleService.getVehicleReportById(vehicleId);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

}
