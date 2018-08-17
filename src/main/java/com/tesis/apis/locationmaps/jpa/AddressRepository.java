package com.tesis.apis.locationmaps.jpa;

import com.tesis.apis.locationmaps.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>{
    Address findByAddress(String address);

}
