package com.tesis.apis.locationmaps.entity;

import javax.persistence.Column;
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
    @Column(name = "unitid")
    private int unitid;
    @Column(name = "locationid")
    private int locationid;
    @Column(name = "nameUnit")
    private String nameUnit;
    @Column(name = "status")
    private String status;

    public UMoviles(){}
    
    public UMoviles(int locationID, String nameUnit, String status) {
        this.locationid = locationID;
        this.nameUnit = nameUnit;
        this.status = status;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public int getLocationid() {
        return locationid;
    }

    public void setLocationID(int locationID) {
        this.locationid = locationID;
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

    @Override
    public String toString() {
        return "UMoviles{" + "unitid=" + unitid + ", locationid=" + locationid + ", nameUnit=" + nameUnit + ", status=" + status + '}';
    }
    
}
