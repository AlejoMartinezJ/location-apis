package com.tesis.apis.locationmaps.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="umoviles")
public class UMoviles {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "unitid")
    private Integer unitid;
    @Column(name = "locationid")
    private Integer locationid;
    @Column(name = "nameUnit")
    private String nameUnit;
    @Column(name = "status")
    private String status;
    @OneToMany(
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Spots> spots = new ArrayList<>();
    
    public UMoviles(){
        this.status = "CREATED";
    }
    
    public UMoviles(Integer locationID, String nameUnit, String status) {
        this.locationid = locationID;
        this.nameUnit = nameUnit;
        this.status = status;
        this.spots = new ArrayList<>();
    }

    public Integer getUnitid() {
        return unitid;
    }

    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
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

    public List<Spots> getSpots() {
        return spots;
    }
    
    public void setSpots(List<Spots> routes) {       
        //this.spots = routes;
        this.spots.clear();
        if (routes != null) {
            this.spots.addAll(routes);
        }
    }

    @Override
    public String toString() {
        return "UMoviles{" + "unitid=" + unitid + ", locationid=" + locationid + ", nameUnit=" + nameUnit + ", status=" + status + '}';
    }
    
}
