package com.tesis.apis.locationmaps.service.impl;

import Model.Position;
import Model.Track;
import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.jpa.SpotsRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.SpotsService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotsServiceImpl implements SpotsService{
    
    private static final Logger logger = LoggerFactory.getLogger(SpotsServiceImpl.class);
    private final SpotsRepository spotsRepository;
    private final UnitsRepository unitsRepository;
    private final LocationRepository locationRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public SpotsServiceImpl(SpotsRepository spotsRepository, 
                            UnitsRepository unitsRepository,
                            RestTemplate restTemplate,
                            LocationRepository locationRepository){
        this.spotsRepository = spotsRepository;
        this.unitsRepository = unitsRepository;
        this.restTemplate = restTemplate;
        this.locationRepository = locationRepository;
    }   
    @Override
    public List<Object[]> getSpotsOfUnit(Integer id) {
         List<Object[]> allPositions = new ArrayList<>();
         Optional<UMoviles> umovil = unitsRepository.findById(id);
         if (umovil.isPresent()){
             UMoviles unit = umovil.get();
             List<Spots> spots = unit.getSpots();             
             Collections.sort(spots, new Comparator<Spots>() {
               public int compare(Spots o1, Spots o2) {          
                  return o2.getSecuence().compareTo(o1.getSecuence());
               }
             });    
             for(Spots spot : spots){
                Optional<Location> loc = locationRepository.findById(spot.getLocationid());
                if (loc.isPresent()){
                    allPositions.add(getObjectFromLocation(loc.get(), spot.getSecuence()));
                }
             }
         } 
 
         return allPositions;
    }
    
    @Override
    public List<Object[]> getPositionOfAllUnits(List<UMoviles> units) {
        List<Object[]> allPositions = new ArrayList<>();
        int i = 1; 
        for (UMoviles u : units){
            logger.info("Looking up " + u);
            String url = String.format("http://localhost:8092/tracker/%s", u.getNameUnit());
            Track result = restTemplate.getForObject(url, Track.class);
            allPositions.add(new Object[]{u.getNameUnit(), result.getLat(), result.getLongitude(),i});
            i++;           
        };
        return allPositions;
    }
    private Object[] getObjectFromLocation(Location loc, Integer i){
        System.out.println(loc.getLat() + ' ' + loc.getLng());
        return new Object[]{loc.getPlaceName(), loc.getLat(),loc.getLng(), i};
    }
    
}
