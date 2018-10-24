package com.tesis.apis.locationmaps.controller;

import Model.Position;
import Model.TimeDriving;
import com.google.maps.errors.ApiException;
import com.tesis.apis.locationmaps.entity.UMoviles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tesis.apis.locationmaps.service.LocationService;
import com.tesis.apis.locationmaps.service.MathModelService;
import com.tesis.apis.locationmaps.service.PositionService;
import com.tesis.apis.locationmaps.service.SpotsService;
import com.tesis.apis.locationmaps.service.UMovilesService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

@RestController
public class LocationControllers {
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private MathModelService mathModelService;
    
    @Autowired
    private PositionService positionService;
   
    @Autowired
    private SpotsService spotsService;

    @Autowired
    private UMovilesService unitsService;

    @RequestMapping("/test")
    public String redirToList() {
        return "Congratulation the webApp is working";
    }

    @RequestMapping("/test/json")
    public ResponseEntity<Position> Get() {
        Position myPosition = new Position();
        //myPosition.setAddress("address\":\"1600 Pennsylvania Ave NW, Washington, DC 20500, USA");
        myPosition.setLat("38.8976633");
        myPosition.setLng("-77.0365739");
        return new ResponseEntity<>(myPosition, HttpStatus.OK);
    }

    @RequestMapping(value = "/maps/location/{address}")
    public @ResponseBody
    Position showLocationByAddress(@PathVariable("address") String address) {
        Position position = new Position();
        try {
            position = locationService.findLocationAddress(address);
        } catch (ApiException | InterruptedException | IOException | ClassNotFoundException e) {
        }
        return position;  
    }

    @RequestMapping(value = "/maps/distance", method = RequestMethod.POST)
    public ResponseEntity<TimeDriving> PostPositions(@RequestBody List<String> positions) {
        TimeDriving timeDriving = new TimeDriving();
        try {
            timeDriving = locationService.findTimeDriveBetweenTwoPoint(positions.get(0), positions.get(1));
        } catch (ApiException | InterruptedException | IOException | ClassNotFoundException e) {
        }
        return new ResponseEntity<>(timeDriving, HttpStatus.OK);
    }
 
    @RequestMapping("/units/route/{id}")
    public ModelAndView routePage(@PathVariable("id") Integer id) {
        Map<String,Object> model = new HashMap<String,Object>();
        List<Object[]> allPositions = new ArrayList<>();        
        allPositions = spotsService.getSpotsOfUnit(id);
        Object[] init = allPositions.get(0);
        allPositions.add(init); // end
        allPositions.forEach(o -> System.out.println(o[0].toString()));
        //allPositions.add(new Object[]{"Point1", -11.9622, -77.08372, 2});
        //allPositions.add(new Object[]{"Point3", -11.95116, -77.0775, 5});
        //allPositions.add(new Object[]{"Point4", -11.9481, -77.06248, 1});
        //allPositions.add(new Object[]{"Point5", -11.95859, -77.05789, 3});
        //allPositions.add(new Object[]{"Point2", -11.96703, -77.06986, 4});
        //allPositions.add(new Object[]{"Point1", -11.9622, -77.08372, 2});
        model.put("positions", allPositions);
        return new ModelAndView("showUnitRoute", "model", model);
    }
    @RequestMapping("/units/positions")
    public ModelAndView positionsPage() {
        Map<String,Object> model = new HashMap<String,Object>();
        List<Object[]> allPositions = new ArrayList<>();        
        List<UMoviles> units = unitsService.findAllActiveUnits();
        allPositions = spotsService.getPositionOfAllUnits(units);
        allPositions.forEach(o -> System.out.println(o[1].toString() + " " + o[2].toString()));    
        //allPositions.add(new Object[]{"UI1002", -11.9622, -77.08372, 2});
        //allPositions.add(new Object[]{"UI1003", -11.95116, -77.0775, 5});
        //allPositions.add(new Object[]{"UI2001", -11.9481, -77.06248, 1});
        
        model.put("positions", allPositions);
        return new ModelAndView("showUnitsPositions", "model", model);
    }
}
