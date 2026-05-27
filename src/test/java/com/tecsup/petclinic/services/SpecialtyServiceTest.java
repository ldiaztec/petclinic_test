package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(SpecialtyServiceImpl.class)
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    public void testCreateSpecialty() {
        entityManager.createNativeQuery(
            "ALTER TABLE specialties ALTER COLUMN id RESTART WITH 100")
            .executeUpdate();

        Specialty specialty = new Specialty(null, "cardiology", "Central", 9, 17);
        Specialty saved = specialtyService.create(specialty);
        assertNotNull(saved.getId());
        assertEquals(100, saved.getId());
        log.info("Specialty creada: {}", saved);
    }

    @Test
    public void testFindSpecialtyById() {
        // radiology ya existe con ID 1
        Specialty found = specialtyService.findById(1);
        assertNotNull(found);
        assertEquals("radiology", found.getName());
        log.info("Specialty encontrada: {}", found);
    }

    @Test
    public void testFindAllSpecialties() {
        var specialties = specialtyService.findAll();
        assertFalse(specialties.isEmpty());
        assertEquals(3, specialties.size()); // hay 3 en data.sql
        log.info("Total specialties: {}", specialties.size());
    }

    @Test
    public void testFindByName() {
        var specialties = specialtyService.findByName("surgery");
        assertFalse(specialties.isEmpty());
        assertEquals("surgery", specialties.get(0).getName());
        log.info("Specialties encontradas por nombre: {}", specialties.size());
    }

    @Test
    public void testUpdateSpecialty() {
        Specialty specialty = specialtyService.findById(2);
        specialty.setName("surgery updated");
        Specialty updated = specialtyService.update(specialty);
        assertEquals("surgery updated", updated.getName());
        log.info("Specialty actualizada: {}", updated);
    }

    @Test
    public void testDeleteSpecialty() {
        specialtyService.delete(3);
        assertNull(specialtyService.findById(3));
        log.info("Specialty eliminada correctamente");
    }
}