package com.tesis.apis.locationmaps.entity;

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
    
    @Column(name = "origenid")
    private Integer origenid;
    
    @Column(name = "destinationid")
    private Integer destinationid;    
    
    @Column(name = "distance")
    private int distance;    

    @Column(name = "timeDriving")
    private int timeDriving; 

    public Distance(Integer origenid, Integer destinationid, Integer distance, Integer timeDriving) {
        this.origenid = origenid;
        this.destinationid = destinationid;
        this.distance = distance;
        this.timeDriving = timeDriving;
    }

    public Distance() {
    }

    public Integer getOrigenid() {
        return origenid;
    }

    public void setOrigenid(Integer origenid) {
        this.origenid = origenid;
    }

    public Integer getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(Integer destinationid) {
        this.destinationid = destinationid;
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
        return "Distance{" + "id=" + distanceid + ", origenID=" + origenid + ", destinationID=" + destinationid + ", distance=" + distance + ", timeDriving=" + timeDriving + '}';
    }
}
