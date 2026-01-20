package com.next.routeMaster.repository;

import com.next.routeMaster.entity.RouteAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RouteAssaignRepo  extends JpaRepository<RouteAssignment,Long> {
    List<RouteAssignment> findByRouteId(Long routeId);
    boolean existsByRouteIdAndStudentId(Long routeId,Long StudentId);
    Long countByRouteId(Long routeId);
}
