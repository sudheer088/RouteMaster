package com.next.routeMaster.controller;

import com.next.routeMaster.entity.Route;
import com.next.routeMaster.service.RouteService;
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
    public Route updateStatus(@PathVariable Long routeId,
                              @PathVariable String status){
        return routeService.updatedStatus(routeId,status);
    }
    @GetMapping("/{routeId}/status")
    public String getStatus(@PathVariable Long routeId){
        return routeService.getStatus(routeId);
    }
}
