package com.tesis.apis.locationmaps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="umoviles")
public class UMoviles {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    private int unitid;
    private int locationID;
    private String nameUnit;
    private String status;

    public UMoviles(){}
    
    public UMoviles(int id,int locationID, String nameUnit, String status) {
        this.unitid = id;
        this.locationID = locationID;
        this.nameUnit = nameUnit;
        this.status = status;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int id) {
        this.unitid = id;
    }
    
    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }           

}
