package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(VisitServiceImpl.class)
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    public void testCreateVisit() {
        entityManager.createNativeQuery(
            "ALTER TABLE visits ALTER COLUMN id RESTART WITH 100")
            .executeUpdate();

        Visit visit = new Visit(null, 1, 1,
                LocalDate.of(2024, 6, 15), "consulta general", 65.00);
        Visit saved = visitService.create(visit);
        assertNotNull(saved.getId());
        assertEquals(100, saved.getId());
        log.info("Visit creada: {}", saved);
    }

    @Test
    public void testFindVisitById() {
        // visita ID 1 ya existe en data.sql
        Visit found = visitService.findById(1);
        assertNotNull(found);
        assertEquals("rabies shot", found.getDescription());
        log.info("Visit encontrada: {}", found);
    }

    @Test
    public void testFindAllVisits() {
        var visits = visitService.findAll();
        assertFalse(visits.isEmpty());
        assertEquals(6, visits.size()); // hay 6 en data.sql
        log.info("Total visits: {}", visits.size());
    }

    @Test
    public void testFindByPetId() {
        // pet ID 7 tiene 2 visitas en data.sql
        var visits = visitService.findByPetId(7);
        assertFalse(visits.isEmpty());
        log.info("Visits para pet 7: {}", visits.size());
    }

    @Test
    public void testFindByVetId() {
        // vet ID 2 tiene 2 visitas en data.sql
        var visits = visitService.findByVetId(2);
        assertFalse(visits.isEmpty());
        log.info("Visits para vet 2: {}", visits.size());
    }

    @Test
    public void testUpdateVisit() {
        Visit visit = visitService.findById(1);
        visit.setDescription("rabies shot updated");
        Visit updated = visitService.update(visit);
        assertEquals("rabies shot updated", updated.getDescription());
        log.info("Visit actualizada: {}", updated);
    }

    @Test
    public void testDeleteVisit() {
        visitService.delete(6);
        assertNull(visitService.findById(6));
        log.info("Visit eliminada correctamente");
    }
}