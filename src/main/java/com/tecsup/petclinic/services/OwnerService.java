package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import java.util.List;

public interface OwnerService {

    Owner save(Owner owner);

    Owner findById(Integer id) throws OwnerNotFoundException;

    List<Owner> findAll();

    void delete(Owner owner);
}