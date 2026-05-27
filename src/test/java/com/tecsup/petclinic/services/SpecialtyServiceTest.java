package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class SpecialtyServiceTest {

    @Autowired
    SpecialtyService specialtyService;

    @Test
    public void testCreateSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setName("Cardiología");

        Specialty saved = specialtyService.save(specialty);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    public void testFindSpecialtyById() {
        Specialty specialty = new Specialty();
        specialty.setName("Neurología");
        Specialty saved = specialtyService.save(specialty);

        try {
            Specialty found = specialtyService.findById(saved.getId());
            assertEquals("Neurología", found.getName());
        } catch (SpecialtyNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setName("Dermatología");
        Specialty saved = specialtyService.save(specialty);

        saved.setName("DermatologíaActualizada");
        specialtyService.save(saved);

        try {
            Specialty updated = specialtyService.findById(saved.getId());
            assertEquals("DermatologíaActualizada", updated.getName());
        } catch (SpecialtyNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setName("Oftalmología");
        Specialty saved = specialtyService.save(specialty);
        int id = saved.getId();

        specialtyService.delete(saved);

        assertThrows(SpecialtyNotFoundException.class, () -> {
            specialtyService.findById(id);
        });
    }
}