package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.Route;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Integer>{
   
}
