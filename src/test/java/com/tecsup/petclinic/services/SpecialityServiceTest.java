package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.dtos.SpecialityDTO;
import com.tecsup.petclinic.exceptions.SpecialityNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional 
@Slf4j
public class SpecialityServiceTest {

    @Autowired
    private SpecialityService specialityService;

    @Test
    public void testCreateSpecialty() {
        SpecialityDTO newSpecialty = new SpecialityDTO();
        newSpecialty.setName("Cardiology");
        newSpecialty.setOffice("Centennial Room 102");
        newSpecialty.setH_open(8);
        newSpecialty.setH_close(16);

        SpecialityDTO savedSpecialty = specialityService.create(newSpecialty);
        log.info("Especialidad Creada: {}", savedSpecialty);

        assertNotNull(savedSpecialty.getId(), "El ID autoincremental no debería ser nulo");
        assertEquals("Cardiology", savedSpecialty.getName());
    }

    @Test
    public void testFindSpecialtyById() {
        Integer idTarget = 1; 
        try {
            SpecialityDTO specialty = specialityService.findById(idTarget);
            log.info("Especialidad Encontrada: {}", specialty);
            
            assertNotNull(specialty);
            assertEquals("radiology", specialty.getName());
        } catch (SpecialityNotFoundException e) {
            fail("No se debió lanzar SpecialityNotFoundException para el ID 1: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateSpecialty() {
        Integer idTarget = 3; 
        try {
            SpecialityDTO specialty = specialityService.findById(idTarget);
            String newOffice = "Terranova Premium Suite";
            specialty.setOffice(newOffice);

            SpecialityDTO updatedSpecialty = specialityService.update(specialty);
            log.info("Especialidad Actualizada: {}", updatedSpecialty);

            assertEquals(newOffice, updatedSpecialty.getOffice());
        } catch (SpecialityNotFoundException e) {
            fail("Error al actualizar (Especialidad no encontrada): " + e.getMessage());
        }
    }

    @Test
    public void testDeleteSpecialty() {
        SpecialityDTO tempSpecialty = new SpecialityDTO();
        tempSpecialty.setName("Oncology");
        tempSpecialty.setOffice("North Wing");
        tempSpecialty.setH_open(7);
        tempSpecialty.setH_close(15);

        SpecialityDTO saved = specialityService.create(tempSpecialty);
        Integer idToDelete = saved.getId();

        try {
            specialityService.delete(idToDelete);

            SpecialityNotFoundException exception = assertThrows(SpecialityNotFoundException.class, () -> {
                specialityService.findById(idToDelete);
            });
            assertNotNull(exception.getMessage());
            
        } catch (SpecialityNotFoundException e) {
            fail("Falló el proceso de flujo de eliminación: " + e.getMessage());
        }
    }
}