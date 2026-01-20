package com.next.routeMaster.controller;

import com.next.routeMaster.entity.RouteAssignment;
import com.next.routeMaster.service.RouteAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class RouteAssignmentController {

    private RouteAssignmentService assignmentService;

    public RouteAssignmentController(RouteAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/route/{routeId}/student/{studentId}")
    public RouteAssignment assign(@PathVariable Long routeId,
                                  @PathVariable Long studentId) {
        return assignmentService.assignStudent(routeId, studentId);
    }

    @GetMapping("/route/{routeId}")
    public List<RouteAssignment> getStudentsByRoute(@PathVariable Long routeId) {
        return assignmentService.getStudentsByRoute(routeId);
    }
}
