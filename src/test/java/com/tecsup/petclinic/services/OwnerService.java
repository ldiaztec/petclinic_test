package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;

public interface OwnerService {

    /**
     * Crea un nuevo dueño
     */
    Owner create(Owner owner);

    /**
     * Actualiza un dueño existente
     */
    Owner update(Owner owner);

    /**
     * Elimina un dueño por su ID
     */
    void delete(Integer id);

    /**
     * Busca un dueño por su ID
     */
    Owner findById(Integer id);
}