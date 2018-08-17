package com.tesis.apis.locationmaps.service.impl;

import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.entity.URoute;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.jpa.RouteRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.RouteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService{
    
    private final RouteRepository routeRepository;
    private final UnitsRepository unitsRepository;
    private final LocationRepository locationRepository;
    
    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, 
                            UnitsRepository unitsRepository,
                            LocationRepository locationRepository){
        this.routeRepository = routeRepository;
        this.unitsRepository = unitsRepository;
        this.locationRepository = locationRepository;
    }
    
    @Override
    public List<Object[]> getRouteOfUnit(Integer id) {
         List<Object[]> allPositions = new ArrayList<>();
         Optional<UMoviles> umovil = unitsRepository.findById(id);
         if (umovil.isPresent()){
             UMoviles unit = umovil.get();
             allPositions.add(getObjectFromLocation(locationRepository.findByLocationid(unit.getLocationID()), 1));
             List<URoute> routes = routeRepository.findByUnitID(unit.getUnitid());         
             for(URoute route : routes){
                allPositions.add(getObjectFromLocation(locationRepository.findByLocationid(route.getLocationID()), route.getSecuence()));
             }
             allPositions.add(getObjectFromLocation(locationRepository.findByLocationid(unit.getLocationID()), allPositions.size()));
         } 
         return allPositions;
    }
    private Object[] getObjectFromLocation(Location loc, Integer i){
        System.out.println(loc.getLat() + ' ' + loc.getLng());
        return new Object[]{loc.getPlaceName(), loc.getLat(),loc.getLng(), i};
    }
}
