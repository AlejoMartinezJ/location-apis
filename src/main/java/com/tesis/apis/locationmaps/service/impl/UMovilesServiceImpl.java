package com.tesis.apis.locationmaps.service.impl;

import Model.TimeDriving;
import com.google.maps.errors.ApiException;
import com.tesis.apis.locationmaps.entity.Factor;
import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.FactorRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import com.tesis.apis.locationmaps.service.LocationService;
import com.tesis.apis.locationmaps.service.MathModelService;
import com.tesis.apis.locationmaps.service.SpotsService;
import com.tesis.apis.locationmaps.service.UMovilesService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UMovilesServiceImpl implements UMovilesService{
    
    private final LocationService locationService;
    private final FactorRepository factorRepository;
    private final UnitsRepository unitsRepository;
    private final MathModelService mathModelService;
    private final SpotsService spotsService;
    
    @Autowired
    public UMovilesServiceImpl(LocationService locationService,
            UnitsRepository unitsRepository,
            MathModelService mathModelService,
            SpotsService spotsService,
            FactorRepository factorRepository) {
        this.locationService = locationService;
        this.unitsRepository = unitsRepository;
        this.mathModelService = mathModelService;
        this.spotsService = spotsService;
        this.factorRepository = factorRepository;
    }
    @Override
    public void calcNewRouteAsync(Integer UnitId){
        Optional<UMoviles> obj = unitsRepository.findById(UnitId);
        if (obj.isPresent()){
            UMoviles unit = obj.get();
            unit.setStatus("WORKING");
            unitsRepository.save(unit);
            optimizeRouteOfUnit(unit);
        }
    }
    
    private void optimizeRouteOfUnit(UMoviles unit){
            List<Spots> spots = unit.getSpots();
            List<Spots> s = buildMatrixOfTime(spots);
            unit.setSpots(s);
            unit.setStatus("ACTIVE");
            unitsRepository.save(unit);       
    }
    
    private List<Spots> buildMatrixOfTime(List<Spots> positions)
    {   //  index    locationid   
        //0,1,2,3,4  [1,4,5,2,3]
        Spots[] model = positions.toArray(new Spots[positions.size()]);
        Integer[] hmodel = new Integer[model.length];
              
        for (int i = 0; i< model.length; i++){
            hmodel[i] = model[i].getLocationid();
            //System.out.println(model[i].getSecuence() + "  " + model[i].getLocationid());
        }
        
        Integer[] vmodel = hmodel;                
        int md = model.length;    
        
        Integer[][] routeModel = new Integer[md][md];
        for (int i = 0; i < md ; i++)
        {
            for (int j = 0; j < md ; j++){
                if (i == j){
                    routeModel[i][j] = Integer.MAX_VALUE;
                } else {
                    Optional<Factor> obj = factorRepository.findByOrigenAndDestination(vmodel[i],
                        hmodel[j]);
                    if(obj.isPresent()){
                        System.out.println("time found " + obj.get().getTime());
                        routeModel[i][j] = obj.get().getTime();
                    }else{
                        routeModel[i][j] = deriveTimeDriveBetweenTwoPoint(vmodel[i], hmodel[j]);
                        factorRepository.save(new Factor(vmodel[i], 
                                                 hmodel[j],
                                                 routeModel[i][j],0));
                    }
                }
            }    
        }                       
        System.out.println("Matrix:");
       // for(int j=0; j< md; j++){
       //     for (int k=0; k< md; k++){
       //         System.out.print(routeModel[j][k] + ", ");
       //     }
       //     System.out.println("");
       // } 
        
        List<Integer> model1 = mathModelService.resolveTspModel(routeModel);
        
        List<Spots> result = new ArrayList<>();
        model1.remove(model1.size()-1);
        int j = 0;
        for(Integer i: model1){            
            model[i].setSecuence(j+1);
            result.add(model[i]);
            j++;
        }             
        result.forEach(s -> System.out.println(s.getSecuence() + " " + s.getLocationid()));       
        return result;
    }
    
    private String getPositionFromSpot(Integer location){       
        Location obj = locationService.findByLocationID(location);      
        StringBuilder s = new StringBuilder(obj.getLat());
        s.append(",");
        s.append(obj.getLng());
        return s.toString();
    }
    
    private Integer deriveTimeDriveBetweenTwoPoint(Integer origen, Integer destination)
    {
        System.out.println("call cloud to get time entre " + origen + " and " + destination);
        String ori = getPositionFromSpot(origen);
        String des = getPositionFromSpot(destination);
        TimeDriving timeDriving = new TimeDriving();
        try {
            timeDriving = locationService.findTimeDriveBetweenTwoPoint(ori, des);
        } catch (ApiException | InterruptedException | IOException | ClassNotFoundException e) {
        }
        
        return timeDriving.getTime();
        
        /*
        Optional<Factor> obj = factorRepository.findByOrigenAndDestination(origen.getLocationid(), destination.getLocationid());
        if (!obj.isPresent()){

        Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_DISTANCE_ENDPOINT, Map.class, encode(latA, "UTF-8"),encode(lngA, "UTF-8"), encode(latB, "UTF-8"),encode(lngB, "UTF-8")); 
        
        String status = (String) obj.get("status"); 
        if (!status.equals(STATUS_OK)) {        
            throw new RuntimeException(buildMessage(status)); 
        }        
        List<?> rows = (List<?>) obj.get("rows");
        
        Map<?,?> results = (Map<?,?>) rows.get(0);
        List<?> result = (List<?>) results.get("elements");
        Map<?,?> element = (Map<?,?>) result.get(0);
        Map<?, ?> distance = (Map<?, ?>) element.get("distance"); 
        Map<?, ?> duration = (Map<?, ?>) element.get("duration"); 
 
        TimeDriving newTimeDriving = new TimeDriving();
        newTimeDriving.setOrigin(latA+","+lngA);
        newTimeDriving.setDestination(latB+","+lngB);
       
        newTimeDriving.setTime((Integer)duration.get("value"));
        newTimeDriving.setDistance((Integer) distance.get("value"));
        } else {
            Factor distance = obj.get();
        }
        return newTimeDriving;
        */
    }

    @Override
    public List<UMoviles> findAllActiveUnits() {
           return  unitsRepository.findAll().stream()
                   .filter(u -> "ACTIVE".equals(u.getStatus()))
                   .collect(Collectors.toList());
    }
}
