/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.apis.locationmaps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locationid")
    private Integer locationid;
    
    @Column(name = "addressid")
    private Integer addressid;
    
    @Column(name = "placeName")
    private String placeName;
  
    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    public Location(Integer addressId, String placeName, String lat, String lng) {
        this.addressid = addressId;
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
    }

    public Location() {
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }
    
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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
