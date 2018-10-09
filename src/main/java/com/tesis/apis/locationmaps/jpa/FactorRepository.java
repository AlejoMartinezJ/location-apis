package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.Factor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactorRepository extends JpaRepository<Factor, Integer>{
    Optional<Factor> findByOrigenAndDestination(Integer orig, Integer dest);
}
