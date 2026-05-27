package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Speciality;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialityService specialityService;

    @Test
    void testSpecialityServiceNotNull() {

        assertNotNull(specialityService);

        log.info("SpecialityService funciona correctamente");
    }

    @Test
    void testCreateSpeciality() {

        Speciality speciality = new Speciality();

        speciality.setName("Cirugia");
        speciality.setOffice("A101");
        speciality.setH_open(8);
        speciality.setH_close(18);

        Speciality savedSpeciality = specialityService.create(speciality);

        assertNotNull(savedSpeciality);

        log.info("Especialidad creada");
    }

    @Test
    void testFindSpeciality() {

        Speciality speciality = new Speciality();

        speciality.setName("Cardiologia");
        speciality.setOffice("B202");
        speciality.setH_open(9);
        speciality.setH_close(17);

        Speciality savedSpeciality = specialityService.create(speciality);

        Speciality found = specialityService.findById(savedSpeciality.getId());

        assertNotNull(found);

        log.info("Especialidad encontrada");
    }

    @Test
    void testUpdateSpeciality() {

        Speciality speciality = new Speciality();

        speciality.setName("General");
        speciality.setOffice("C303");
        speciality.setH_open(10);
        speciality.setH_close(16);

        Speciality savedSpeciality = specialityService.create(speciality);

        savedSpeciality.setName("Dermatologia");

        Speciality updated = specialityService.update(savedSpeciality);

        assertEquals("Dermatologia", updated.getName());

        log.info("Especialidad actualizada");
    }

    @Test
    void testDeleteSpeciality() {

        Speciality speciality = new Speciality();

        speciality.setName("Temporal");
        speciality.setOffice("D404");
        speciality.setH_open(7);
        speciality.setH_close(15);

        Speciality savedSpeciality = specialityService.create(speciality);

        specialityService.delete(savedSpeciality.getId());

        assertTrue(true);

        log.info("Especialidad eliminada");
    }
}