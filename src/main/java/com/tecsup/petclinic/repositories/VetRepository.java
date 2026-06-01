package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VetRepository extends CrudRepository<Vet, Integer> {
    // Método personalizado para buscar por apellido si se necesita luego
    List<Vet> findByLastName(String lastName);
}