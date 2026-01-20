package com.next.routeMaster.repository;

import com.next.routeMaster.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo  extends JpaRepository<Vehicle,Long> {
    List<Vehicle> findByRouteId(Long routeId);
}
