package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.UMoviles;
import org.springframework.data.repository.CrudRepository;

public interface UnitsRepository extends CrudRepository<UMoviles, Integer>{
    
}
