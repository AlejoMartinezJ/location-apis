package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.URoute;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<URoute, Integer>{
    List<URoute> findByUnitID(Integer unitID);
}
