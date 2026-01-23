package com.next.routeMaster.service;

import com.next.routeMaster.dto.StudentDto;
import com.next.routeMaster.entity.Route;
import com.next.routeMaster.entity.RouteAssignment;
import com.next.routeMaster.entity.Student;
import com.next.routeMaster.repository.RouteAssaignRepo;
import com.next.routeMaster.repository.RouteRepo;
import com.next.routeMaster.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteAssignmentService {
    private final RouteAssaignRepo routeAssaignRepo;
    private final RouteRepo routeRepo;
    private final StudentRepo studentRepo;

    public RouteAssignmentService(RouteAssaignRepo routeAssaignRepo, RouteRepo routeRepo, StudentRepo studentRepo) {
        this.routeAssaignRepo = routeAssaignRepo;
        this.routeRepo = routeRepo;
        this.studentRepo = studentRepo;
    }

    public RouteAssignment assignStudent(Long routeId, Long studentId) {
        if (routeAssaignRepo.existsByRouteIdAndStudentId(routeId, studentId)) {
            throw new RuntimeException("student already assaigned to this route");
        }
        Route route = routeRepo.findById(routeId).orElseThrow(() -> new RuntimeException("route not found"));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
        RouteAssignment assignment = new RouteAssignment();
        assignment.setRoute(route);
        assignment.setStudent(student);
        return routeAssaignRepo.save(assignment);
    }

    public List<StudentDto> getStudentsByRoute(Long routeId) {

        List<RouteAssignment> list = routeAssaignRepo.findByRouteId(routeId);

        return list.stream()
                .map(ra -> new StudentDto(
                        ra.getStudent().getId(),
                        ra.getStudent().getName(),
                        ra.getStudent().getGrade(),
                        ra.getStudent().getParent_number()
                ))
                .toList();
    }
}

