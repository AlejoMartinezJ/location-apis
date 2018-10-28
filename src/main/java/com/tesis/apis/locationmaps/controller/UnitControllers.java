package com.tesis.apis.locationmaps.controller;

import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.UMovilesService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UnitControllers {
    
    @Autowired
    private UnitsRepository unitsRepository;
    @Autowired
    private UMovilesService umovilesService;
    
    @RequestMapping("/units")
    public String showUnits(Model model) {
        model.addAttribute("allUnits",unitsRepository.findAll());    
        return "listUnits";
    }

    @RequestMapping(value = "/units/tsp/{id}")
    public String CalcTspRoute(Model model, @PathVariable("id") Integer unitId) {       
        if(null != unitId){
            umovilesService.calcNewRouteAsync(unitId);            
        }       
        return "redirect:/units";        
    }
    
    @RequestMapping(value = {"/unitEdit","/unitEdit/{id}"}, method = RequestMethod.GET)
    public String editUnit(Model model,@PathVariable(required=false,name="id") Integer unitId){
        if(null != unitId){
            Optional<UMoviles> unit = unitsRepository.findById(unitId);
            if (unit.isPresent()){
                model.addAttribute("umoviles", unit.get());
            }else{
                model.addAttribute("umoviles", new UMoviles());
            }
        } else{
            model.addAttribute("umoviles", new UMoviles());
        }
        return "editUnit";
    }	 
     
    @RequestMapping(value = "/unitEdit",method = RequestMethod.POST, params={"save"})
    public String saveUnit(Model model,UMoviles umoviles) {
        if(umoviles.getSpots().isEmpty()){
            Spots spot = new Spots();
            spot.setLocationid(umoviles.getLocationid());
            spot.setSecuence(1);
            umoviles.getSpots().add(spot);
        }
        unitsRepository.save(umoviles);       
        return "redirect:/unitEdit/"+umoviles.getUnitid();
    }
    
    @RequestMapping(value = "/unitEdit",method = RequestMethod.POST, params={"addRoute"})
    public String addRouteUnit(UMoviles umoviles) {
        umoviles.addSpot(new Spots());
        unitsRepository.save(umoviles);
        return "redirect:/unitEdit/"+umoviles.getUnitid();
    }
    
    @RequestMapping(value = "/unitEdit",method = RequestMethod.POST, params={"removeRoute"})
    public String removeRouteUnit(UMoviles umoviles, HttpServletRequest req) {        
        final Integer routeId = Integer.valueOf(req.getParameter("removeRoute"));
        umoviles.removeSpot(routeId);
        unitsRepository.save(umoviles);
        return "redirect:/unitEdit/"+umoviles.getUnitid();
    }
    
    @RequestMapping("/units/calls")
    public String receiveCalls(Model model) {   
        return "emergencyCalls";
    }

    @RequestMapping(value = "/unitDelete/{id}", method = RequestMethod.GET)
    public String deleteUnit(@PathVariable("id") Integer Id, Model model) {
    	unitsRepository.deleteById(Id);
        model.addAttribute("allUnits",unitsRepository.findAll());
        return "listUnits";
    }    
}
