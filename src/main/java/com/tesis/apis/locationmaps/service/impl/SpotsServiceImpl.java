package com.tesis.apis.locationmaps.service.impl;

import Model.Position;
import Model.TimeDriving;
import Model.Track;
import Model.UnidadesDto;
import com.google.maps.errors.ApiException;
import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.SpotsRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.LocationService;
import com.tesis.apis.locationmaps.service.SpotsService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotsServiceImpl implements SpotsService{
    
    private static final Logger logger = LoggerFactory.getLogger(SpotsServiceImpl.class);
    private final SpotsRepository spotsRepository;
    private final UnitsRepository unitsRepository;
    private final LocationService locationService;
    private final RestTemplate restTemplate;
    @Autowired
    public SpotsServiceImpl(SpotsRepository spotsRepository, 
                            UnitsRepository unitsRepository,
                            RestTemplate restTemplate,
                            LocationService locationService){
        this.spotsRepository = spotsRepository;
        this.unitsRepository = unitsRepository;
        this.restTemplate = restTemplate;
        this.locationService = locationService;
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
                Location loc = locationService.findByLocationID(spot.getLocationid());              
                allPositions.add(getObjectFromLocation(loc, spot.getSecuence()));                
             }
         }  
         return allPositions;
    }
    
    @Override
    public List<Object[]> getPositionOfAllUnits(List<UMoviles> units) {
        List<Object[]> allPositions = new ArrayList<>();
        int i = 1; 
        for (UMoviles u : units){
            String url = String.format("http://localhost:8092/tracker/%s", u.getNameUnit());
            Track result = restTemplate.getForObject(url, Track.class);
            allPositions.add(new Object[]{u.getNameUnit(), result.getLat(), result.getLongitude(),i});
            i++;           
        };
        return allPositions;
    }
    @Override
    public List<UnidadesDto> getListClosestUnits(List<Object[]> units, Position pos) {
        List<UnidadesDto> dtos = new ArrayList<>();
        
        StringBuilder ori = new StringBuilder(pos.getLat());
        ori.append(",");
        ori.append(pos.getLng());
        
        for (Object[] u : units){           
            String des = getPositionFromLatLng(u[1].toString(), u[2].toString());
            TimeDriving tm = new TimeDriving();
            try {
                tm = locationService.findTimeDriveBetweenTwoPoint(ori.toString(), des);
               
            } catch (ApiException | InterruptedException | IOException | ClassNotFoundException e) {           
            }  
            dtos.add(new UnidadesDto(unitsRepository.findByNameUnit(u[0].toString()).getUnitid(), u[0].toString(),convertSecondsToHours(tm.getTime()), tm.getDistance()));                            
        };
        
        //dtos.sort(Comparator.comparing(o -> o.getrTime()));
        List opt = dtos.stream().sorted((o1, o2)->o1.getrTime().
                                   compareTo(o2.getrTime())).
                                   collect(Collectors.toList());
        
        return opt;
    }   
    private Object[] getObjectFromLocation(Location loc, Integer i){
        System.out.println(loc.getLat() + ' ' + loc.getLng());
        return new Object[]{loc.getPlaceName(), loc.getLat(),loc.getLng(), i};
    }   
    private String getPlaceName(Integer loc){
        Location obj = locationService.findByLocationID(loc);
        return obj.getPlaceName();
    } 
    private String getPositionFromLatLng(String lat, String lng){             
        StringBuilder s = new StringBuilder(lat);
        s.append(",");
        s.append(lng);
        return s.toString();
    }
    private String convertSecondsToHours(Integer seconds){
        int p1 = seconds % 60;
	int p2 = seconds / 60;
	int p3 = p2 % 60;

	p2 = p2 / 60;
        
        return p2 + ":" + p3 + ":" + p1;
    }
}
