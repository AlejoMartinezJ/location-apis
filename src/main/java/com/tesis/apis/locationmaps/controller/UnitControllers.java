package com.tesis.apis.locationmaps.controller;

import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UnitControllers {
    
    @Autowired
    private UnitsRepository unitsRepository; 

    @RequestMapping("/units")
    public String index(Model model) {
	List<UMoviles> moviles = (List<UMoviles>) unitsRepository.findAll();
	model.addAttribute("units", moviles);
    	return "units";
    }

    @RequestMapping(value = "add")
    public String addUnit(Model model){
    	model.addAttribute("unit", new UMoviles());
        return "addUnit";
    }	

    @RequestMapping(value = "/edit/{id}")
    public String editUnit(@PathVariable("id") Integer studentId, Model model){
    	model.addAttribute("unit", unitsRepository.findById(studentId));
        return "editUnit";
    }	    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(UMoviles unit){
        unitsRepository.save(unit);
    	return "redirect:/units";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUnit(@PathVariable("id") Integer studentId, Model model) {
    	unitsRepository.deleteById(studentId);
        return "redirect:/units";
    }    
    
    @RequestMapping(value = "getunits", method = RequestMethod.GET)
    public @ResponseBody List<UMoviles> getUnits() {
            return (List<UMoviles>)unitsRepository.findAll();
    }
     
}
