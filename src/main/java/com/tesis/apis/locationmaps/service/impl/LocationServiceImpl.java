package com.tesis.apis.locationmaps.service.impl;

import Model.Position;
import Model.TimeDriving;
import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import java.io.UnsupportedEncodingException;
import static java.net.URLEncoder.encode;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.tesis.apis.locationmaps.service.LocationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{
    
    public static final String STATUS_OK = "OK"; 
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS"; 
    public static final String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT"; 
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED"; 
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST"; 

        // google maps api endpoint 
    public static final String GOOGLE_MAPS_API_LOCATION_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key=AIzaSyDrcB0UHD76EIItQksUCzP10DY3OZGLHaE"; 
    //public static final String GOOGLE_MAPS_API_LOCATION_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?&address={address}&sensor=false"; 

    public static final String GOOGLE_MAPS_API_DISTANCE_ENDPOINT = "http://maps.googleapis.com/maps/api/distancematrix/json?origins={latA},{lngA}&destinations={latB},{lngB}&mode=driving&language=en-EN&key=AIzaSyDrcB0UHD76EIItQksUCzP10DY3OZGLHaE"; 

    private final RestTemplate restTemplate;
   
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(RestTemplate restTemplate, 
            LocationRepository locationRepository) {
        this.restTemplate = restTemplate;
        this.locationRepository = locationRepository;
    }
       
    @Override
    public Position findLocationAddress(String address) throws RestClientException, UnsupportedEncodingException {
        
        Position newPosition = new Position();
        Optional<Location> objLoc = locationRepository.findByAddress(address);
        if (objLoc.isPresent()){ 
                Location loc = objLoc.get();
                newPosition.setAddress(address);
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
        
        newPosition.setAddress(address);
        newPosition.setLat(location.get("lat").toString());
        newPosition.setLng(location.get("lng").toString());
        
        locationRepository.save(new Location(address, address,newPosition.getLat().toString(), newPosition.getLng().toString()));
        
        return newPosition;
        
    }
    
    @Override
    public TimeDriving findTimeDriveBetweenTwoPoint(String latA, String lngA, String latB, String lngB) throws RestClientException, UnsupportedEncodingException {
   
        Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_DISTANCE_ENDPOINT, Map.class, encode(latA, "UTF-8"),encode(lngA, "UTF-8"), encode(latB, "UTF-8"),encode(lngB, "UTF-8")); 
       
        // check the response status 
        String status = (String) obj.get("status"); 
        if (!status.equals(STATUS_OK)) {        
            throw new RuntimeException(buildMessage(status)); 
        } 
        
        List<?> rows = (List<?>) obj.get("rows");
        
        Map<?,?> results = (Map<?,?>) rows.get(0);
        List<?> result = (List<?>) results.get("elements");
        Map<?,?> element = (Map<?,?>) result.get(0);
        Map<?, ?> distance = (Map<?, ?>) element.get("distance"); 
        Map<?, ?> duration = (Map<?, ?>) element.get("duration"); 
 
        TimeDriving newTimeDriving = new TimeDriving();
        newTimeDriving.setOrigin(latA+","+lngA);
        newTimeDriving.setDestination(latB+","+lngB);
       
        newTimeDriving.setTime((Integer)duration.get("value"));
        newTimeDriving.setDistance((Integer) distance.get("value"));
        
        return newTimeDriving;
    }
    @Override
    public Integer[][] buildMatrixOfTime(List<String> positions, String placeBase)
    {
           
        List<Integer> bestRoute = new ArrayList<>();
        Map<Integer, Position> model = new HashMap<>();
        
        int i = 1;
        for (String obj : positions){
            model.put(i, getPositionFromAddress(obj));
            i++;
        }
     
        Position base = getPositionFromAddress(placeBase);
        //model.put(0, base);
        
        Integer[] route = model.keySet().toArray(new Integer[model.keySet().size()]);
        
        int md = positions.size() + 1;
        
        Integer[][] routeModel = new Integer[md][md];
        for (i = 0; i < md ; i++)
        {
            for (int j = 0; j < md ; j++){
                if (i == j){
                    routeModel[i][j] = Integer.MAX_VALUE;
                }
            }    
        }
        
        for (int j=0; j< route.length; j++){
            try {
                routeModel[0][j+1] = findTimeDriveBetweenTwoPoint(
                        base.getLat(),
                        base.getLng(),
                        model.get(route[j]).getLat(),
                        model.get(route[j]).getLng()).getTime();
            } catch (UnsupportedEncodingException | RestClientException ex) {
                            return null;
            }           
        }    
            
        for (int j=0; j< route.length; j++){
            try {
                routeModel[j+1][0] = findTimeDriveBetweenTwoPoint(
                        model.get(route[j]).getLat(),
                        model.get(route[j]).getLng(),
                        base.getLat(),
                        base.getLng()).getTime();
            } catch (UnsupportedEncodingException | RestClientException ex) {
                            return null;
            }           
        }  
        
        for (int j=0; j< route.length; j++)
        {
          for (int k=0; k< route.length; k++){
              if ( j != k){
                try {
                    routeModel[j+1][k+1] = findTimeDriveBetweenTwoPoint(
                        model.get(route[j]).getLat(),
                        model.get(route[j]).getLng(),
                        model.get(route[k]).getLat(),
                        model.get(route[k]).getLng()).getTime();
                }catch (UnsupportedEncodingException | RestClientException ex) {
                        return null;
                }    
              }
          }         
        }    
        
        System.out.println("Matrix:");
        for(int j=0; j< md; j++){
            for (int k=0; k< md; k++){
                System.out.print(routeModel[j][k] + ", ");
            }
            System.out.println("");
        }
        
        return routeModel;
    }
    @Override
    public List<Location> getAllHotSpots(){
        return locationRepository.findAll();
    }
    
    @Override
    public void deleteHotSpot(Integer id){
        locationRepository.deleteById(id);
    }
    /*
    @Override
    public Location saveHotSpot(Location location){
            
        Optional<Location> objLoc = locationRepository.findByAddress(location.getAddress());
        if (objLoc.isPresent()){ 
                return objLoc.get();
        }           
        try{        
            Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_LOCATION_ENDPOINT, Map.class, encode(location.getAddress(), "UTF-8")); 
            String status = (String) obj.get("status"); 
            if (!status.equals(STATUS_OK)) { 
               throw new RuntimeException(buildMessage(status)); 
            }
            List<?> results = (List<?>) obj.get("results"); 
            Map<?, ?> result = (Map<?, ?>) results.get(0); 
            Map<?, ?> geometry = (Map<?, ?>) result.get("geometry"); 
            Map<?, ?> gpsloc = (Map<?, ?>) geometry.get("location"); 
        
            location.setLat(gpsloc.get("lat").toString());
            location.setLng(gpsloc.get("lng").toString());
            location.setPlaceName(location.getAddress());
            locationRepository.save(location);
 
            return location;
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        // check the response status 
        return location;
    }
    */
    @Override
    public Location saveHotSpot(Location location){          
           return locationRepository.save(location);

    }
    private String buildMessage(String status) { 
        if (status == null ? STATUS_ZERO_RESULTS == null : status.equals(STATUS_ZERO_RESULTS)) 
            return "No result is found"; 
        else if (status.equals(STATUS_OVER_QUERY_LIMIT)) 
            return "You are over your quota"; 
        else if (status.equals(STATUS_REQUEST_DENIED)) 
            return "Your request was denied"; 
        else if (status.equals(STATUS_INVALID_REQUEST)) 
            return "The query is missing"; 
 
        return ""; 
    } 
    private Position getPositionFromAddress(String address){       
        Optional<Location> location = locationRepository.findByAddress(address);
        if (location.isPresent()){
            return new Position(address,location.get().getLat(), location.get().getLng(),0);
        }
        return null;
    }
    
}
