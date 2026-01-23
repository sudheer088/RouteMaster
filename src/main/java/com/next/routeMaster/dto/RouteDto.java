package com.next.routeMaster.dto;

public class RouteDto {
    private Long routeId;
    private String routeName;
    private String startPoint;
    private String status;

    public RouteDto(Long routeId, String routeName, String startPoint, String status) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.startPoint = startPoint;
        this.status = status;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
