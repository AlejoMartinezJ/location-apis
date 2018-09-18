/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.Location;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer>{
        Optional<Location> findByAddress(String address);     
        Location findByLatAndLng(String lat,String lng);
}
