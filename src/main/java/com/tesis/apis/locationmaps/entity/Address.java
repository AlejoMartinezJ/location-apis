
package com.tesis.apis.locationmaps.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressid; 
    @Column(name = "address")
    private String address;    
    @Column(name = "placeName")
    private String placeName;
    
    protected Address() { }
    public Address(String address, String placeName) {
        this.address = address;
        this.placeName = placeName;
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }       
    @Override
    public String toString() {
        return "Address{" + "id=" + addressid + ", address=" + address + '}';
    }  



}
