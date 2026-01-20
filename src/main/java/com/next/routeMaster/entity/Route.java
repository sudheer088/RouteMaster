package com.next.routeMaster.entity;

import jakarta.persistence.*;

@Entity
@Table(name="route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String route_name;
    @Column(nullable = false)
    private String Start;
    @Column(nullable=false)
    private String end;
    @Column(nullable=false)
    private String status;

    public Route() {
    }

    public Route(String route_name, String start, String end, String status) {
        this.route_name = route_name;
        this.Start = start;
        this.end = end;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
