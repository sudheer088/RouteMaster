package com.next.routeMaster.repository;

import com.next.routeMaster.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehicleRepo  extends JpaRepository<Vehicle,Long> {
    List<Vehicle> findByRouteId(Long routeId);
}
