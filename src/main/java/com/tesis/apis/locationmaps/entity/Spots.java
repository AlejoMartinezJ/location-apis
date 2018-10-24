package com.tesis.apis.locationmaps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spots")
public class Spots {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
    private Integer spotid; 
    @Column(name="locationid")
    private Integer locationid;
    @Column(name="secuence")
    private Integer secuence;

    public Spots() {
    }

    public Spots(Integer locationid, Integer secuence) {
        this.locationid = locationid;
        this.secuence = secuence;
    }

    public Integer getSpotid() {
        return spotid;
    }

    public void setSpotid(Integer spotid) {
        this.spotid = spotid;
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
        return "Spots{" + "routeid=" + spotid + ", locationid=" + locationid + ", secuence=" + secuence + '}';
    }
        
}
