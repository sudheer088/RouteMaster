package com.next.routeMaster.controller;

import com.next.routeMaster.entity.Route;
import com.next.routeMaster.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }
    @PostMapping("/add")
    public Route createRoute(@RequestBody Route route){
        return routeService.createRoute(route);
    }
    @PatchMapping("/{routeId}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable Long routeId,
                              @PathVariable String status){
        try{
            return ResponseEntity.ok(routeService.updatedStatus(routeId,status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());

        }
    }
    @GetMapping("/{routeId}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long routeId){
        try{

            return ResponseEntity.ok(routeService.getStatus(routeId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
