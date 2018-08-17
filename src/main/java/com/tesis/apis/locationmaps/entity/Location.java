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
@Table(name = "location")
public class Location implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer locationid;
    
    @Column(name = "addressid")
    private Integer addressid;
    
    @Column(name = "placeName")
    private String placeName;
  
    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    public Location(Integer locationID, String placeName, String lat, String lng) {
        this.addressid = locationID;
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
    }

    public Location() {
    }
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public long getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer locationID) {
        this.addressid = locationID;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + locationid +", locationID=" + addressid + ", lat=" + lat + ", lng=" + lng + '}';
    }
    
}
