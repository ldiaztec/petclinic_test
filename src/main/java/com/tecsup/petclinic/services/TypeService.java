package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;

public interface TypeService {

    Type save(Type type);

    Type findById(Integer id) throws Exception;

    void deleteById(Integer id) throws Exception;
}