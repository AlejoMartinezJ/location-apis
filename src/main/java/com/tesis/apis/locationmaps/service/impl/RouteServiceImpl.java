package com.tesis.apis.locationmaps.service.impl;

import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Route;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.jpa.RouteRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.RouteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
             List<Route> routes = unit.getRoutes();         
             for(Route route : routes){
                Optional<Location> loc = locationRepository.findById(route.getLocationid());
                if (loc.isPresent()){
                    allPositions.add(getObjectFromLocation(loc.get(), route.getSecuence()));
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
