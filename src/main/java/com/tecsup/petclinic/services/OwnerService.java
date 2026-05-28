package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import java.util.List;

public interface OwnerService {
    Owner save(Owner owner);
    Owner findById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
    List<Owner> findByLastName(String lastName);
}