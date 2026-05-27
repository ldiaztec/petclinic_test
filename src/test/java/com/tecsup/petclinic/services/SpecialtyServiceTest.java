package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Speciality;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {
    @Autowired
    private SpecialityService specialityService;

    @Test
    public void testCreateSpeciality() {
        Speciality speciality = Speciality.builder()
                .name("cardiology")
                .build();

        Speciality createdSpeciality = specialityService.create(speciality);

        assertNotNull(createdSpeciality);
        assertNotNull(createdSpeciality.getId());
        assertEquals("cardiology", createdSpeciality.getName());

        log.info("Especialidad creada: {}", createdSpeciality);
    }

    @Test
    public void testFindSpecialityById() {
        Speciality speciality = specialityService.findById(1);

        assertNotNull(speciality);
        assertNotNull(speciality.getName());

        log.info("Especialidad encontrada: {}", speciality);
    }

    @Test
    public void testFindAllSpecialities() {
        List<Speciality> specialities = specialityService.findAll();

        assertNotNull(specialities);
        assertFalse(specialities.isEmpty());

        log.info("Cantidad de especialidades encontradas: {}", specialities.size());
    }

    @Test
    public void testUpdateSpeciality() {
        Speciality speciality = specialityService.findById(1);

        assertNotNull(speciality);

        speciality.setName("updated-speciality");

        Speciality updatedSpeciality = specialityService.update(speciality);

        assertEquals("updated-speciality", updatedSpeciality.getName());

        log.info("Especialidad actualizada: {}", updatedSpeciality);
    }

    @Test
    public void testDeleteSpeciality() {
        Speciality speciality = Speciality.builder()
                .name("temporary-speciality")
                .build();

        Speciality createdSpeciality = specialityService.create(speciality);
        Integer id = createdSpeciality.getId();

        specialityService.delete(id);

        Speciality deletedSpeciality = specialityService.findById(id);

        assertNull(deletedSpeciality);

        log.info("Especialidad eliminada con ID: {}", id);
    }

    @Test
    public void testFindSpecialityByName() {
        List<Speciality> specialities = specialityService.findByName("radiology");

        assertNotNull(specialities);

        log.info("Especialidades encontradas por nombre: {}", specialities);
    }
}