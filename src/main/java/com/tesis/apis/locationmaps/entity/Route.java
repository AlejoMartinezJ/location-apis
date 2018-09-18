
package com.tesis.apis.locationmaps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="routes")
public class Route {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
    private Integer routeid; 
    @Column(name="locationid")
    private Integer locationid;
    @Column(name="secuence")
    private Integer secuence;
    
    public Route() {
    }

    public Route(Integer locationid, Integer secuence) {
        this.locationid = locationid;
        this.secuence = secuence;
    }

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getSecuence() {
        return secuence;
    }

    public void setSecuence(Integer secuence) {
        this.secuence = secuence;
    }

    @Override
    public String toString() {
        return "Route{" + "routeid=" + routeid + ", locationid=" + locationid + ", secuence=" + secuence + '}';
    }
    
}
