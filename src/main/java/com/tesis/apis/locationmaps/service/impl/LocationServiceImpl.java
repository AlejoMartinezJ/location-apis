package com.tesis.apis.locationmaps.service.impl;

import Model.Position;
import Model.TimeDriving;
import java.io.UnsupportedEncodingException;
import static java.net.URLEncoder.encode;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.tesis.apis.locationmaps.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
    
    public static final String STATUS_OK = "OK"; 
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS"; 
    public static final String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT"; 
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED"; 
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST"; 

        // google maps api endpoint 
    public static final String GOOGLE_MAPS_API_LOCATION_ENDPOINT = "http://maps.googleapis.com/maps/api/geocode/json?address={address}&sensor=false"; 
    public static final String GOOGLE_MAPS_API_DISTANCE_ENDPOINT = "http://maps.googleapis.com/maps/api/distancematrix/json?origins={latA},{lngA}&destinations={latB},{lngB}&mode=driving&language=en-EN&sensor=false"; 

    private RestTemplate restTemplate;

    @Autowired
    public LocationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Override
    public Position findLocationAddress(String address) throws RestClientException, UnsupportedEncodingException {

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
 
        Position newPosition = new Position();
        newPosition.setAddress(address);
        newPosition.setLat(String.valueOf(location.get("lat")));
        newPosition.setLng(String.valueOf(location.get("lng")));
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
    
    private String buildMessage(String status) { 
        if (status == STATUS_ZERO_RESULTS) 
            return "No result is found"; 
        else if (status == STATUS_OVER_QUERY_LIMIT) 
            return "You are over your quota"; 
        else if (status == STATUS_REQUEST_DENIED) 
            return "Your request was denied"; 
        else if (status == STATUS_INVALID_REQUEST) 
            return "The query is missing"; 
 
        return ""; 
    } 
}
