package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testAssignSpecialtyToVet() {
        // Asignamos al veterinario 1 la especialidad 2
        VetSpecialtyDTO result = vetSpecialtyService.assignSpecialty(1, 2);
        log.info("Relación asignada correctamente: {}", result);

        assertNotNull(result);
        assertEquals(1, result.getVetId());
        assertEquals(2, result.getSpecialtyId());
    }

    @Test
    public void testFindSpecialtiesByVet() {
        // Garantizamos una relación previa
        vetSpecialtyService.assignSpecialty(3, 1);

        List<Integer> specialties = vetSpecialtyService.findSpecialtiesByVet(3);
        log.info("Especialidades del veterinario con ID 3: {}", specialties);

        assertFalse(specialties.isEmpty());
        assertTrue(specialties.contains(1));
    }

    @Test
    public void testFindVetsBySpecialty() {
        // Garantizamos una relación previa
        vetSpecialtyService.assignSpecialty(2, 3);

        List<Integer> vets = vetSpecialtyService.findVetsBySpecialty(3);
        log.info("Veterinarios con la especialidad ID 3: {}", vets);

        assertFalse(vets.isEmpty());
        assertTrue(vets.contains(2));
    }

    @Test
    public void testRemoveVetSpecialtyRelation() {

        vetSpecialtyService.assignSpecialty(4, 2);
        
 
        List<Integer> beforeDelete = vetSpecialtyService.findSpecialtiesByVet(4);
        assertTrue(beforeDelete.contains(2));


        vetSpecialtyService.removeRelation(4, 2);
        log.info("Relación entre Veterinario 4 y Especialidad 2 eliminada con éxito");

  
        List<Integer> afterDelete = vetSpecialtyService.findSpecialtiesByVet(4);
        assertFalse(afterDelete.contains(2));
    }
}