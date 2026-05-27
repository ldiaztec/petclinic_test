package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    @Test
    public void testCreateSpecialty() {
        SpecialtyDTO specialtyDTO = SpecialtyDTO.builder()
                .name("Dermatology")
                .build();

        SpecialtyDTO expectedSpecialty = specialtyService.create(specialtyDTO);
        log.info("Especialidad creada con éxito: {}", expectedSpecialty);

        assertNotNull(expectedSpecialty.getId());
        assertEquals("Dermatology", expectedSpecialty.getName());
    }

    @Test
    public void testFindSpecialtyById() {
        SpecialtyDTO temp = specialtyService.create(SpecialtyDTO.builder()
                .name("Radiology")
                .build());

        try {
            SpecialtyDTO specialtyFound = specialtyService.findById(temp.getId());
            log.info("Especialidad encontrada por ID: {}", specialtyFound);

            assertNotNull(specialtyFound);
            assertEquals("Radiology", specialtyFound.getName());
        } catch (SpecialtyNotFoundException e) {
            fail("No debió fallar la búsqueda de la especialidad: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateSpecialty() {
        SpecialtyDTO temp = specialtyService.create(SpecialtyDTO.builder()
                .name("Surgery")
                .build());

        try {
            temp.setName("Surgery - Cardio");
            SpecialtyDTO updatedSpecialty = specialtyService.update(temp);
            log.info("Especialidad modificada con éxito: {}", updatedSpecialty);

            assertEquals("Surgery - Cardio", updatedSpecialty.getName());
        } catch (SpecialtyNotFoundException e) {
            fail("No debió fallar la actualización de la especialidad: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteSpecialty() {
        SpecialtyDTO temp = specialtyService.create(SpecialtyDTO.builder()
                .name("Odontology")
                .build());

        Integer idToDelete = temp.getId();

        try {
            specialtyService.delete(idToDelete);
            log.info("Especialidad con ID {} eliminada correctamente", idToDelete);
        } catch (SpecialtyNotFoundException e) {
            fail("Falló la eliminación de la especialidad: " + e.getMessage());
        }

        assertThrows(SpecialtyNotFoundException.class, () -> {
            specialtyService.findById(idToDelete);
        });
    }
}