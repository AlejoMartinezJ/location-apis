package com.tesis.apis.locationmaps.controller;

import Model.Position;
import Model.TimeDriving;
import com.tesis.apis.locationmaps.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tesis.apis.locationmaps.service.LocationService;
import com.tesis.apis.locationmaps.service.MathModelService;
import com.tesis.apis.locationmaps.service.PositionService;
import com.tesis.apis.locationmaps.service.RouteService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.client.RestClientException;
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
    private RouteService routeService;
    
    @RequestMapping("/test")
    public String redirToList() {
        return "Congratulation the webApp is working";
    }

    @RequestMapping("/test/json")
    public ResponseEntity<Position> Get() {
        Position myPosition = new Position();
        myPosition.setAddress("address\":\"1600 Pennsylvania Ave NW, Washington, DC 20500, USA");
        myPosition.setLat("38.8976633");
        myPosition.setLng("-77.0365739");
        return new ResponseEntity<>(myPosition, HttpStatus.OK);
    }

    @RequestMapping(value = "/test/distance", method = RequestMethod.POST)
    public ResponseEntity<Position> PostDistance(@RequestBody Position position) {
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

    @RequestMapping(value = "/maps/location/{address}")
    public @ResponseBody
    Position showLocationByAddress(@PathVariable("address") String address) {
        Position position = new Position();
        try {
            position = locationService.findLocationAddress(address);
        } catch (UnsupportedEncodingException | RestClientException e) {
        }
        return position;  
    }

    @RequestMapping(value = "/maps/distance", method = RequestMethod.POST)
    public ResponseEntity<TimeDriving> PostPositions(@RequestBody List<Position> positions) {
        TimeDriving timeDriving = new TimeDriving();
        try {
            timeDriving = locationService.findTimeDriveBetweenTwoPoint(positions.get(0).getLat(), positions.get(0).getLng(), positions.get(1).getLat(), positions.get(1).getLng());
        } catch (UnsupportedEncodingException | RestClientException e) {
        }
        return new ResponseEntity<>(timeDriving, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/maps/tsp/{baseID}", method = RequestMethod.POST)
    public ResponseEntity<List<Position>> CalcTspRoute(@RequestBody List<String> positions, @PathVariable("baseID") String base) {
        List<Position> route = new ArrayList<>();       
        Integer[][] matrix = locationService.buildMatrixOfTime(positions, base); 
        List<Integer> model = mathModelService.resolveTspModel(matrix);
        List<Position> UIModel = positionService.getUIPositionRoute(model);
        return new ResponseEntity<>(UIModel, HttpStatus.OK);        
    }
    
    @RequestMapping("/route/{id}")
    public ModelAndView routePage(@PathVariable("id") Integer id) {
        Map<String,Object> model = new HashMap<String,Object>();
        List<Object[]> allPositions = new ArrayList<>();        
        allPositions = routeService.getRouteOfUnit(id);       
        //allPositions.add(new Object[]{"Point1", -11.9622, -77.08372, 2});
        //allPositions.add(new Object[]{"Point3", -11.95116, -77.0775, 5});
        //allPositions.add(new Object[]{"Point4", -11.9481, -77.06248, 1});
        //allPositions.add(new Object[]{"Point5", -11.95859, -77.05789, 3});
        //allPositions.add(new Object[]{"Point2", -11.96703, -77.06986, 4});
        //allPositions.add(new Object[]{"Point1", -11.9622, -77.08372, 2});
        model.put("positions", allPositions);
        return new ModelAndView("showUnits", "model", model);
    }
    
}
