package com.tesis.apis.locationmaps.service;

import Model.Position;
import Model.UnidadesDto;
import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import java.util.List;

public interface SpotsService {
    List<Object[]> getSpotsOfUnit(Integer id);
    List<Object[]> getPositionOfAllUnits(List<UMoviles> units);
    List<Object[]> getListClosestUnits(List<UMoviles> units, Position position);
}
