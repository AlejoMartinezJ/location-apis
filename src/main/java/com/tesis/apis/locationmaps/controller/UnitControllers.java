package com.tesis.apis.locationmaps.controller;

import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.SpotsService;
import com.tesis.apis.locationmaps.service.UMovilesService;
import java.util.ArrayList;
import java.util.List;
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
        //model.addAttribute("allUnits",unitsRepository.findAll());
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
  /*
    @RequestMapping(value="/units/save", method = RequestMethod.POST, params={"addRoute"})
    public String addRoute(UMoviles umoviles, Model model) {
        umoviles.getRoutes().add(new Route());
        return "redirect:/editUnit";
    }
       
    @RequestMapping(value="/units/remove", params={"removeRoute"})
    public String removeRoute(final UMoviles umoviles, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer routeId = Integer.valueOf(req.getParameter("removeRoute"));
        umoviles.getRoutes().remove(routeId.intValue());
        return "unitManager";
    }
    
    @RequestMapping(value = "/add")
    public String addUnit(Model model){
    	model.addAttribute("umoviles", new UMoviles());
        return "addUnit";
    }	

    @RequestMapping(value = "/units/edit/{id}", method = RequestMethod.GET)
    public String editUnit(@PathVariable("id") Integer unitId, Model model){
        Optional<UMoviles> unit = unitsRepository.findById(unitId);
	if (unit.isPresent()){
            model.addAttribute("umoviles", unit.get());
        }else{
            model.addAttribute("umoviles", new UMoviles());
        }
    	//model.addAttribute("umoviles", unitsRepository.findById(unitId).get());
        return "redirecteditUnit";
    }	    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(UMoviles unit){
        System.out.println(unit.toString());
        unitsRepository.save(unit);
    	return "redirect:/units";
    }
    */
    
    @RequestMapping(value = "/unitDelete/{id}", method = RequestMethod.GET)
    public String deleteUnit(@PathVariable("id") Integer Id, Model model) {
    	unitsRepository.deleteById(Id);
        model.addAttribute("allUnits",unitsRepository.findAll());
        return "listUnits";
    }    
    /*
    @RequestMapping(value = "addUnitRoute/{id}", method = RequestMethod.GET)
    public String addRoute(@PathVariable("id") Integer unitId, Model model){
                model.addAttribute("route", new Route());
    		model.addAttribute("umoviles", unitsRepository.findById(unitId).get());
    		return "addUnitRoute";
    }    */
}
