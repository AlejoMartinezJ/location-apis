
package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.Spots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotsRepository extends JpaRepository<Spots, Integer>{
    
}
