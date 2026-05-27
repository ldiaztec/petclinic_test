package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialityDTO;
import com.tecsup.petclinic.exceptions.SpecialityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialityServiceTest {

    @Autowired
    private SpecialityService specialityService;

    @Test
    public void testCreateSpeciality() {

        String NAME = "Cardiology";

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name(NAME)
                .build();

        SpecialityDTO newSpecialityDTO = this.specialityService.create(specialityDTO);

        log.info("ESPECIALIDAD CREADA: {}", newSpecialityDTO);

        assertNotNull(newSpecialityDTO);
        assertNotNull(newSpecialityDTO.getId());
        assertEquals(NAME, newSpecialityDTO.getName());
    }

    @Test
    public void testUpdateSpeciality() {

        String NAME = "Dentistry";
        String UPDATED_NAME = "Veterinary Dentistry";

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name(NAME)
                .build();

        SpecialityDTO newSpecialityDTO = this.specialityService.create(specialityDTO);

        newSpecialityDTO.setName(UPDATED_NAME);

        SpecialityDTO updatedSpecialityDTO = this.specialityService.update(newSpecialityDTO);

        log.info("ESPECIALIDAD ACTUALIZADA: {}", updatedSpecialityDTO);

        assertNotNull(updatedSpecialityDTO);
        assertEquals(newSpecialityDTO.getId(), updatedSpecialityDTO.getId());
        assertEquals(UPDATED_NAME, updatedSpecialityDTO.getName());
    }

    @Test
    public void testFindSpecialityById() {

        String NAME = "Surgery";

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name(NAME)
                .build();

        SpecialityDTO newSpecialityDTO = this.specialityService.create(specialityDTO);

        SpecialityDTO specialityFound = null;

        try {
            specialityFound = this.specialityService.findById(newSpecialityDTO.getId());
        } catch (SpecialityNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("ESPECIALIDAD ENCONTRADA POR ID: {}", specialityFound);

        assertNotNull(specialityFound);
        assertEquals(newSpecialityDTO.getId(), specialityFound.getId());
        assertEquals(NAME, specialityFound.getName());
    }

    @Test
    public void testFindSpecialityByName() {

        String NAME = "Radiology";

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name(NAME)
                .build();

        this.specialityService.create(specialityDTO);

        List<SpecialityDTO> specialities = this.specialityService.findByName(NAME);

        log.info("ESPECIALIDADES ENCONTRADAS POR NOMBRE: {}", specialities);

        assertNotNull(specialities);
        assertTrue(specialities.size() > 0);
    }

    @Test
    public void testDeleteSpeciality() {

        String NAME = "Neurology";

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name(NAME)
                .build();

        SpecialityDTO newSpecialityDTO = this.specialityService.create(specialityDTO);

        log.info("ESPECIALIDAD A ELIMINAR: {}", newSpecialityDTO);

        try {
            this.specialityService.delete(newSpecialityDTO.getId());
        } catch (SpecialityNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(SpecialityNotFoundException.class, () -> {
            this.specialityService.findById(newSpecialityDTO.getId());
        });
    }
}