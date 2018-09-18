package com.tesis.apis.locationmaps.controller;

import com.tesis.apis.locationmaps.entity.Route;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.RouteRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnitControllers {
    
    @Autowired
    private UnitsRepository unitsRepository; 

    @Autowired
    private RouteRepository routeRepository; 
    
    @ModelAttribute("allUnits")
    public List<UMoviles> populateUnitss() {
        return unitsRepository.findAll();
    }
    
    @RequestMapping("/units")
    public String showUnits(Model model) {
        UMoviles umoviles = new UMoviles();
        model.addAttribute("umoviles",umoviles);     
        return "unitManager";
    }
    
    @RequestMapping(value = "/units/create", method = RequestMethod.POST)
    public String saveUnit(UMoviles umoviles, Model model) {
        //if (bindingResult.hasErrors()) {
        //    return "redirect:/unitManager";
        //}
        
        this.unitsRepository.save(umoviles);
        return "redirect:/units";
    }
      
    @RequestMapping(value="/units/add/route", method = RequestMethod.POST)
    public String addRoute(final UMoviles umoviles, final BindingResult bindingResult) {
        umoviles.getRoutes().add(new Route());
        return "unitManager";
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

    @RequestMapping(value = "/edit/{id}")
    public String editUnit(@PathVariable("id") Integer unitId, Model model){
        System.out.println("id = " + unitId);
        Optional<UMoviles> unit = unitsRepository.findById(unitId);
	if (unit.isPresent()){
            model.addAttribute("umoviles", unit.get());
        }else{
            model.addAttribute("umoviles", new UMoviles());
        }
    	//model.addAttribute("umoviles", unitsRepository.findById(unitId).get());
        return "editUnit";
    }	    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(UMoviles unit){
        System.out.println(unit.toString());
        unitsRepository.save(unit);
    	return "redirect:/units";
    }
    
    @RequestMapping(value = "/units/delete/{id}", method = RequestMethod.GET)
    public String deleteUnit(@PathVariable("id") Integer Id, Model model) {
    	unitsRepository.deleteById(Id);
        return "redirect:/units";
    }    
    
    @RequestMapping(value = "getunits", method = RequestMethod.GET)
    public @ResponseBody List<UMoviles> getUnits() {
            return (List<UMoviles>)unitsRepository.findAll();
    }
    
    @RequestMapping(value = "addUnitRoute/{id}", method = RequestMethod.GET)
    public String addRoute(@PathVariable("id") Integer unitId, Model model){
                model.addAttribute("route", new Route());
    		model.addAttribute("umoviles", unitsRepository.findById(unitId).get());
    		return "addUnitRoute";
    }    
}
