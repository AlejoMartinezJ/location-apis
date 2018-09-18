package com.tesis.apis.locationmaps.controller;

import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HotSpotController {
    
    @Autowired
    private LocationService locationService;
   
    @RequestMapping("/hotSpots")
    public String showUnits(Model model) {
        Location location = new Location();
        model.addAttribute("location", location);
        model.addAttribute("allLocations", locationService.getAllHotSpots());     
        return "HotSpots";
    }
    
    @RequestMapping(value = "/location/delete/{id}", method = RequestMethod.GET)
    public String deleteSpot(@PathVariable("id") Integer id, Model model) {
    	locationService.deleteHotSpot(id);
        model.addAttribute("allLocations", locationService.getAllHotSpots());
        return "redirect:/hotSpots";
    }

    @RequestMapping(value = "/location/save", method = RequestMethod.POST)
    public String save(Model model, Location location){
        locationService.saveHotSpot(location);
        model.addAttribute("allLocations", locationService.getAllHotSpots()); 
    	return "redirect:/hotSpots";
    }    
}
