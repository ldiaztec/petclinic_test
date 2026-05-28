package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialityDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialityServiceTest {

    @Autowired
    private SpecialityService specialityService;

    @Test
    public void testCreateSpeciality() {

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name("Cirugia")
                .build();

        SpecialityDTO createdSpeciality = specialityService.create(specialityDTO);

        assertNotNull(createdSpeciality);
        assertNotNull(createdSpeciality.getId());

        log.info("Speciality creada: " + createdSpeciality);
    }

    @Test
    public void testFindSpecialityById() {

        SpecialityDTO speciality = specialityService.findById(1);

        assertNotNull(speciality);

        log.info("Speciality encontrada: " + speciality);
    }

    @Test
    public void testUpdateSpeciality() {

        SpecialityDTO speciality = specialityService.findById(1);

        speciality.setName("Dermatologia");

        SpecialityDTO updatedSpeciality = specialityService.update(speciality);

        assertEquals("Dermatologia", updatedSpeciality.getName());

        log.info("Speciality actualizada: " + updatedSpeciality);
    }

    @Test
    public void testFindAllSpecialities() {

        var specialities = specialityService.findAll();

        assertNotNull(specialities);

        log.info("Total specialities: " + specialities.size());
    }

    @Test
    public void testDeleteSpeciality() {

        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .name("Temporal")
                .build();

        SpecialityDTO createdSpeciality = specialityService.create(specialityDTO);

        Integer id = createdSpeciality.getId();

        specialityService.delete(id);

        SpecialityDTO deletedSpeciality = specialityService.findById(id);

        assertNull(deletedSpeciality);

        log.info("Speciality eliminada correctamente");
    }
}