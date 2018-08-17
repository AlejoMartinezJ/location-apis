
package com.tesis.apis.locationmaps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uroute")
public class URoute {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    private int routeid;
    private int unitID;
    private int locationID;
    private int secuence;

    public URoute(int id, int unitID, int locationID, int secuence) {
        this.routeid = id;
        this.unitID = unitID;
        this.locationID = locationID;
        this.secuence = secuence;
    }

    public URoute() {
    }

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int id) {
        this.routeid = id;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getSecuence() {
        return secuence;
    }

    public void setSecuence(int secuence) {
        this.secuence = secuence;
    }    
}
