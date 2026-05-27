package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
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
        log.info("Especialidad asignada correctamente: {}", savedRelation);

        // Verificamos los campos clave compuestos
        assertEquals(VET_ID, savedRelation.getVetId());
        assertEquals(SPECIALTY_ID, savedRelation.getSpecialtyId());
    }

    @Test
    public void testFindSpecialtiesByVetId() {
        Integer VET_ID = 2; 
        List<VetSpecialty> specialties = vetSpecialtyService.findByVetId(VET_ID);
        log.info("Especialidades del veterinario con ID {}: {}", VET_ID, specialties);
        assertNotNull(specialties);
    }

    @Test
    public void testFindVetsBySpecialtyId() {
        Integer SPECIALTY_ID = 1; 
        List<VetSpecialty> vets = vetSpecialtyService.findBySpecialtyId(SPECIALTY_ID);
        log.info("Veterinarios con la especialidad ID {}: {}", SPECIALTY_ID, vets);
        assertNotNull(vets);
    }

    @Test
    public void testDeleteVetSpecialtyRelation() {
        Integer VET_ID = 3;
        Integer SPECIALTY_ID = 2;

        VetRelationQuickCreate: {
            VetSpecialty relation = new VetSpecialty();
            relation.setVetId(VET_ID);
            relation.setSpecialtyId(SPECIALTY_ID);
            vetSpecialtyService.save(relation);
        }

        try {
            // Usamos el nuevo método pasándole ambos identificadores
            vetSpecialtyService.delete(VET_ID, SPECIALTY_ID);
            log.info("Relación entre Vet {} y Specialty {} eliminada con éxito", VET_ID, SPECIALTY_ID);
        } catch (Exception e) {
            fail("Falló la eliminación de la relación: " + e.getMessage());
        }
    }
}