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
    public static final String GOOGLE_MAPS_API_DISTANCE_ENDPOINT = "http://maps.googleapis.com/maps/api/distancematrix/json?origins={pointa}&destinations={pointb}&mode=driving&language=en-EN&sensor=false"; 

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
    public TimeDriving findTimeDriveBetweenTwoPoint(String pointa, String pointb) throws RestClientException, UnsupportedEncodingException {
   
       Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_DISTANCE_ENDPOINT, Map.class, encode(pointa, "UTF-8"),encode(pointb, "UTF-8")); 
 
        // check the response status 
        String status = (String) obj.get("status"); 
        if (!status.equals(STATUS_OK)) { 
            throw new RuntimeException(buildMessage(status)); 
        } 
 
        List<?> results = (List<?>) obj.get("rows"); 
        List<?> elements = (List<?>) results.get(0);
        Map<?, ?> result = (Map<?, ?>) elements.get(0);
        Map<?, ?> distance = (Map<?, ?>) result.get("distance"); 
        Map<?, ?> duration = (Map<?, ?>) result.get("duration"); 
 
        TimeDriving newTimeDriving = new TimeDriving();
        newTimeDriving.setOrigin(pointa);
        newTimeDriving.setDestination(pointb);
        newTimeDriving.setTime((Double) duration.get("value"));
        newTimeDriving.setDistance((Double) distance.get("value"));
        
        return new TimeDriving();
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
