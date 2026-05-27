package com.tecsup.petclinic.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@ActiveProfiles("mysql")
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService specialtyService;

    @Test
    public void testAssignSpecialtyToVet() {
        VetSpecialtyDTO relation = new VetSpecialtyDTO();
        relation.setVetId(3);       
        relation.setSpecialtyId(2); 

        VetSpecialtyDTO savedRelation = specialtyService.assignSpecialty(relation);
        log.info("Relación establecida de manera exitosa: {}", savedRelation);

        assertNotNull(savedRelation);
        assertEquals(3, savedRelation.getVetId());
        assertEquals(2, savedRelation.getSpecialtyId());
    }

    @Test
    public void testFindSpecialtiesByVet() {
        Integer targetVetId = 1; 
        
        List<Integer> specialtyIds = specialtyService.findSpecialtiesByVet(targetVetId);
        log.info("Especialidades encontradas para el veterinario {}: {}", targetVetId, specialtyIds);

        assertNotNull(specialtyIds);
        assertFalse(specialtyIds.isEmpty(), "El veterinario debería registrar especialidades");
        assertTrue(specialtyIds.contains(1));
    }

    @Test
    public void testFindVetsBySpecialty() {
        Integer targetSpecialtyId = 1; 
        
        List<Integer> vetIds = specialtyService.findVetsBySpecialty(targetSpecialtyId);
        log.info("Veterinarios calificados en la especialidad {}: {}", targetSpecialtyId, vetIds);

        assertNotNull(vetIds);
        assertFalse(vetIds.isEmpty(), "Deberían existir veterinarios asignados a esta especialidad");
    }

    @Test
    public void testRemoveSpecialtyFromVet() {
        Integer vetId = 1;
        Integer specialtyId = 1;

        try {
            specialtyService.removeSpecialtyFromVet(vetId, specialtyId);
            log.info("Relación entre vet_id: {} y specialty_id: {} eliminada correctamente", vetId, specialtyId);
            
            List<Integer> emptyCheck = specialtyService.findSpecialtiesByVet(999);
            assertTrue(emptyCheck.isEmpty());
            
        } catch (Exception e) {
            fail("El flujo de desasignación lanzó un error inesperado: " + e.getMessage());
        }
    }
}