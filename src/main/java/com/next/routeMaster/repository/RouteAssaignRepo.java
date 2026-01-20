package com.next.routeMaster.repository;

import com.next.routeMaster.entity.RouteAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteAssaignRepo  extends JpaRepository<RouteAssignment,Long> {
    List<RouteAssignment> findByRouteId(Long routeId);
    boolean existByRouteIdAndStudentId(Long routeId,Long StudentId);
    Long countByRouteId(Long routeId);
}
