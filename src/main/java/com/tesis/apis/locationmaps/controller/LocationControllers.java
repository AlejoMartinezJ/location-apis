package com.tesis.apis.locationmaps.controller;

import Model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tesis.apis.locationmaps.service.LocationService;

@RestController
public class LocationControllers {
    
    @Autowired
    private LocationService locationService;

    @RequestMapping("/")
    public String Hello(){
        return "Hello World";
    }
    
    @RequestMapping("/test")
    public String redirToList(){
        return "Congratulation the webApp is working";
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

}
