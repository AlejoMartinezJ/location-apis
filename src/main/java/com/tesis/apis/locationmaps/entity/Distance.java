/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.apis.locationmaps.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distance")
public class Distance{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer distanceid;
    
    @Column(name = "origenID")
    private Integer origenID;
    
    @Column(name = "destinationID")
    private Integer destinationID;    
    
    @Column(name = "distance")
    private int distance;    

    @Column(name = "timeDriving")
    private int timeDriving; 

    public Distance(Integer origenID, Integer destinationID, Integer distance, Integer timeDriving) {
        this.origenID = origenID;
        this.destinationID = destinationID;
        this.distance = distance;
        this.timeDriving = timeDriving;
    }

    public Distance() {
    }

    public Integer getOrigenID() {
        return origenID;
    }

    public void setOrigenID(Integer origenID) {
        this.origenID = origenID;
    }

    public Integer getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTimeDriving() {
        return timeDriving;
    }

    public void setTimeDriving(Integer timeDriving) {
        this.timeDriving = timeDriving;
    }

    @Override
    public String toString() {
        return "Distance{" + "id=" + distanceid + ", origenID=" + origenID + ", destinationID=" + destinationID + ", distance=" + distance + ", timeDriving=" + timeDriving + '}';
    }
}
