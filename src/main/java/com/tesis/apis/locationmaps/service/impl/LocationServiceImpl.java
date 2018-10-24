package com.tesis.apis.locationmaps.service.impl;

import Model.Coord;
import Model.Position;
import Model.TimeDriving;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tesis.apis.locationmaps.service.LocationService;
import java.io.IOException;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{   
    private final LocationRepository locationRepository;
    private final ClientMap clientMap;
    
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ClientMap clientMap) {
        this.locationRepository = locationRepository;
        this.clientMap = clientMap;
    }
       
    @Override
    public Position findLocationAddress(String address) throws ApiException, InterruptedException, IOException, ClassNotFoundException {
        
        Position newPosition = new Position();
        /*
        Optional<Location> objLoc = locationRepository.findByAddress(address);
        if (objLoc.isPresent()){ 
                Location loc = objLoc.get();
                //newPosition.setAddress(address);
                newPosition.setLat(loc.getLat());
                newPosition.setLng(loc.getLng());
                return newPosition;
        }                           
        Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_LOCATION_ENDPOINT, Map.class, encode(address, "UTF-8")); 
        // check the response status 
        String status = (String) obj.get("status"); 
        if (!status.equals(STATUS_OK)) { 
            throw new RuntimeException(buildMessage(status)); 
        }
               
        List<?> results = (List<?>) obj.get("results"); 
        Map<?, ?> result = (Map<?, ?>) results.get(0); 
        Map<?, ?> geometry = (Map<?, ?>) result.get("geometry"); 
        Map<?, ?> location = (Map<?, ?>) geometry.get("location"); 
        */
        //newPosition.setAddress(address);
        
        LatLng tL = clientMap.lookupCoord(address);
        Coord c = new Coord(address, tL);      
        newPosition.setLat(String.valueOf(c.getLat()));       
        newPosition.setLng(String.valueOf(c.getLng()));       
        locationRepository.save(new Location(address, address,newPosition.getLat().toString(), newPosition.getLng().toString()));
        
        return newPosition;       
    }
    
    @Override
    public List<Location> getAllHotSpots(){
        return locationRepository.findAll();
    }
    
    @Override
    public void deleteHotSpot(Integer id){
        locationRepository.deleteById(id);
    }
    
    @Override
    public Location saveHotSpot(Location location){          
           return locationRepository.save(location);
    }
    
    @Override
    public Location findByLocationID(Integer location){
        Optional<Location> obj = locationRepository.findById(location);
        if (obj.isPresent()){
            return obj.get();
        }
        return null;
    }
    @Override
    public TimeDriving findTimeDriveBetweenTwoPoint(String origen, String destination) throws ApiException, InterruptedException, IOException, ClassNotFoundException{  
        Long d = clientMap.getDriveTime(origen, destination);       
        return new TimeDriving(origen, destination, d.intValue(), 0);
    }
}
