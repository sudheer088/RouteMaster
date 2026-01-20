package com.next.routeMaster.repository;

import com.next.routeMaster.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo  extends JpaRepository<Route,Long> {
}
