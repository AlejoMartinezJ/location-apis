package com.tesis.apis.locationmaps.service.impl;

import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.jpa.SpotsRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.SpotsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotsServiceImpl implements SpotsService{
    
    private final SpotsRepository spotsRepository;
    private final UnitsRepository unitsRepository;
    private final LocationRepository locationRepository;
    
    @Autowired
    public SpotsServiceImpl(SpotsRepository spotsRepository, 
                            UnitsRepository unitsRepository,
                            LocationRepository locationRepository){
        this.spotsRepository = spotsRepository;
        this.unitsRepository = unitsRepository;
        this.locationRepository = locationRepository;
    }   
    @Override
    public List<Object[]> getSpotsOfUnit(Integer id) {
         List<Object[]> allPositions = new ArrayList<>();
         Optional<UMoviles> umovil = unitsRepository.findById(id);
         if (umovil.isPresent()){
             UMoviles unit = umovil.get();
             List<Spots> spots = unit.getSpots();         
             for(Spots spot : spots){
                Optional<Location> loc = locationRepository.findById(spot.getLocationid());
                if (loc.isPresent()){
                    allPositions.add(getObjectFromLocation(loc.get(), spot.getSecuence()));
                }
             }
         } 
         return allPositions;
    }
    
    private Object[] getObjectFromLocation(Location loc, Integer i){
        System.out.println(loc.getLat() + ' ' + loc.getLng());
        return new Object[]{loc.getPlaceName(), loc.getLat(),loc.getLng(), i};
    }
    
}
