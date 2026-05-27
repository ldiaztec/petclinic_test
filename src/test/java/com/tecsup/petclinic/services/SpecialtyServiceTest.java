package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2") 
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    @Test
    public void testCreateSpecialty() {
        String NAME = "Dermatology";

        // Usamos constructor tradicional
        Specialty specialty = new Specialty();
        specialty.setName(NAME);

        Specialty savedSpecialty = specialtyService.save(specialty); 
        log.info("Especialidad creada: {}", savedSpecialty);

        Assertions.assertNotNull(savedSpecialty.getId());
        Assertions.assertEquals(NAME, savedSpecialty.getName());
    }

    @Test
    public void testFindSpecialtyById() throws Exception { 
        Specialty specialty = new Specialty();
        specialty.setName("Radiology");
        Specialty savedSpecialty = specialtyService.save(specialty);

        Specialty foundSpecialty = specialtyService.findById(savedSpecialty.getId());
        log.info("Especialidad encontrada: {}", foundSpecialty);

        Assertions.assertEquals(savedSpecialty.getId(), foundSpecialty.getId());
        Assertions.assertEquals("Radiology", foundSpecialty.getName());
    }

    @Test
    public void testUpdateSpecialty() throws Exception { 
        Specialty specialty = new Specialty();
        specialty.setName("Surgery");
        Specialty savedSpecialty = specialtyService.save(specialty);

        savedSpecialty.setName("Advanced Surgery");
        Specialty updatedSpecialty = specialtyService.save(savedSpecialty); 
        log.info("Especialidad actualizada: {}", updatedSpecialty);

        Assertions.assertEquals("Advanced Surgery", updatedSpecialty.getName());
    }

    @Test
    public void testDeleteSpecialty() throws Exception { 
        Specialty specialty = new Specialty();
        specialty.setName("Dentistry");
        Specialty savedSpecialty = specialtyService.save(specialty);
        Integer id = savedSpecialty.getId();

        specialtyService.deleteById(id); 

        Assertions.assertThrows(Exception.class, () -> {
            specialtyService.findById(id);
        });
        log.info("Especialidad con ID {} eliminada correctamente", id);
    }
}