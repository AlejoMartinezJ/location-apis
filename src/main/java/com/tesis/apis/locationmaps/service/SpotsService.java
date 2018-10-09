package com.tesis.apis.locationmaps.service;

import com.tesis.apis.locationmaps.entity.Spots;
import java.util.List;

public interface SpotsService {
    List<Object[]> getSpotsOfUnit(Integer id);
}
