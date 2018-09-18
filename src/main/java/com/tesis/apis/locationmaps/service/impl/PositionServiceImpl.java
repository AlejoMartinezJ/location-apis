
package com.tesis.apis.locationmaps.service.impl;

import Model.Position;
import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.service.PositionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService{
    private final LocationRepository locationRepository;
    
    @Autowired
    public PositionServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Position> getUIPositionRoute(List<Integer> model) {       
        List<Position> listPosition = new ArrayList<>();
        int i = 1;
        for(Integer pos: model){  
            Optional<Location> location = locationRepository.findById(pos + 1);
            if (location.isPresent()){
                Location loc = location.get();
                 listPosition.add(new Position(loc.getPlaceName(), loc.getLat(), loc.getLng(), i));
                 i++;               
            }
        }
        return listPosition;
    }   
}
