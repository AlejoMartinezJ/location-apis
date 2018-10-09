package com.tesis.apis.locationmaps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "factor")
public class Factor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "factorid")
    private Integer factorid;
    private Integer origen;
    private Integer destination;
    private Integer time;
    private Integer distance;

    public Factor(){}
    public Factor(Integer origen, Integer destination, Integer time, Integer distance) {
        this.origen = origen;
        this.destination = destination;
        this.time = time;
        this.distance = distance;
    }

    public Integer getDestination() {
        return destination;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }    
}
