package com.next.routeMaster.entity;

import jakarta.persistence.*;

@Entity
public class RouteAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public RouteAssignment() {
    }

    public RouteAssignment(Route route, Student student) {
        this.route = route;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
