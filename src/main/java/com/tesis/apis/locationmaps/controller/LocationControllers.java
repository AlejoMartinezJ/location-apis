package com.tesis.apis.locationmaps.controller;

import Model.Position;
import Model.TimeDriving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tesis.apis.locationmaps.service.LocationService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LocationControllers {
    
    @Autowired
    private LocationService locationService;
   
    @RequestMapping("/test")
    public String redirToList(){
        return "Congratulation the webApp is working";
    }    
    
    @RequestMapping("/test/json")
    public ResponseEntity<Position> Get(){
        Position myPosition = new Position();
        myPosition.setAddress("address\":\"1600 Pennsylvania Ave NW, Washington, DC 20500, USA");
        myPosition.setLat("38.8976633");
        myPosition.setLng("-77.0365739");
        return new ResponseEntity<>(myPosition, HttpStatus.OK);
    }
        
    @RequestMapping(value= "/test/distance", method = RequestMethod.POST)
    public ResponseEntity<Position> PostDistance(@RequestBody Position position){
        
        if (position != null) {
            position.setAddress(position.getAddress() + " MODIFIED");
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/test/raw", method = POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String greetingJson(String raw) {
        System.out.println("json = " + raw);
        return "OK";
    }
    
    @RequestMapping(value="/maps/location/{address}")
    public @ResponseBody Position showLocationByAddress(@PathVariable("address") String address)
    {
        Position position = new Position();
        try { 
            position = locationService.findLocationAddress(address); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return position;
    }
    
    @RequestMapping(value= "/maps/distance", method = RequestMethod.POST)
    public ResponseEntity<TimeDriving> PostPositions(@RequestBody List<Position> positions){
        
        TimeDriving timeDriving = new TimeDriving();
             
        try { 
            timeDriving = locationService.findTimeDriveBetweenTwoPoint(positions.get(0).getLat(),positions.get(0).getLng(), positions.get(1).getLat(),positions.get(1).getLng()); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        
        return new ResponseEntity<TimeDriving>(timeDriving, HttpStatus.OK);       
    }        
}
