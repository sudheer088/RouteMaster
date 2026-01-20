package com.next.routeMaster.service;

import com.next.routeMaster.entity.Route;
import com.next.routeMaster.repository.RouteRepo;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private RouteRepo routeRepo;
    public RouteService(RouteRepo routeRepo){
        this.routeRepo=routeRepo;
    }
    public Route createRoute(Route route){
        route.setStatus("Not_Started");
        return routeRepo.save(route);
    }
    public Route updatedStatus(Long routeId,String status){
        Route route=routeRepo.findById(routeId).orElseThrow(()-> new RuntimeException("route not found"));
        route.setStatus(status);
        return routeRepo.save(route);
    }
    public String getStatus (Long routeId){
        Route route = routeRepo.findById(routeId).orElseThrow(()-> new RuntimeException("route not found"));
        return route.getStatus();
    }

}
