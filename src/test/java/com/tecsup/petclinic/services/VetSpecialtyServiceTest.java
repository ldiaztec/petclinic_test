package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testAssignSpecialtyToVet() {
        Integer VET_ID = 1;       
        Integer SPECIALTY_ID = 1; 

        VetSpecialty relation = new VetSpecialty();
        relation.setVetId(VET_ID);
        relation.setSpecialtyId(SPECIALTY_ID);

        VetSpecialty savedRelation = vetSpecialtyService.save(relation);
        log.info("Relación Creada (Asignación): {}", savedRelation);

        assertNotNull(savedRelation.getVetId());
        assertEquals(SPECIALTY_ID, savedRelation.getSpecialtyId());
    }

    @Test
    public void testFindSpecialtiesByVet() {
        Integer VET_ID = 2; 
        
        VetSpecialty relation = new VetSpecialty();
        relation.setVetId(VET_ID);
        relation.setSpecialtyId(2); 
        vetSpecialtyService.save(relation);

        List<VetSpecialty> specialties = vetSpecialtyService.findByVetId(VET_ID);
        log.info("Especialidades del Veterinario {}: {}", VET_ID, specialties);

        assertFalse(specialties.isEmpty());
    }

    @Test
    public void testFindVetsBySpecialty() {
        Integer SPECIALTY_ID = 3; 

        VetSpecialty relation = new VetSpecialty();
        relation.setVetId(3); 
        relation.setSpecialtyId(SPECIALTY_ID);
        vetSpecialtyService.save(relation);

        List<VetSpecialty> vets = vetSpecialtyService.findBySpecialtyId(SPECIALTY_ID);
        log.info("Veterinarios con la Especialidad {}: {}", SPECIALTY_ID, vets);

        assertFalse(vets.isEmpty());
    }

    @Test
    public void testDeleteVetSpecialtyRelation() {
        Integer VET_ID = 5;
        Integer SPECIALTY_ID = 2;
        
        VetSpecialty relation = new VetSpecialty();
        relation.setVetId(VET_ID);
        relation.setSpecialtyId(SPECIALTY_ID);
        vetSpecialtyService.save(relation);

        // Creamos la llave compuesta para eliminarla
        VetSpecialtyId idCompuesto = new VetSpecialtyId();
        idCompuesto.setVetId(VET_ID);
        idCompuesto.setSpecialtyId(SPECIALTY_ID);

        try {
            vetSpecialtyService.deleteById(idCompuesto);
            log.info("Confirmado: Relación Vet {} y Specialty {} eliminada con éxito.", VET_ID, SPECIALTY_ID);
        } catch (Exception e) {
            fail("No se pudo eliminar la relación: " + e.getMessage());
        }
    }
}