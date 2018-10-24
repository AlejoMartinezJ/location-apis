package com.tesis.apis.locationmaps.service;

import com.tesis.apis.locationmaps.entity.UMoviles;
import java.util.List;

public interface UMovilesService {  
    void calcNewRouteAsync(Integer unitId);
    List<UMoviles> findAllActiveUnits();
}
