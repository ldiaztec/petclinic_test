package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {
        Visit visit = Visit.builder()
                .petId(1)
                .visitDate(LocalDate.of(2026, 5, 27))
                .description("Control general de mascota")
                .build();

        Visit createdVisit = visitService.create(visit);

        assertNotNull(createdVisit);
        assertNotNull(createdVisit.getId());
        assertEquals(1, createdVisit.getPetId());
        assertEquals("Control general de mascota", createdVisit.getDescription());

        log.info("Visita creada: {}", createdVisit);
    }

    @Test
    public void testFindVisitById() {
        Visit visit = visitService.findById(1);

        assertNotNull(visit);
        assertNotNull(visit.getDescription());

        log.info("Visita encontrada: {}", visit);
    }

    @Test
    public void testFindAllVisits() {
        List<Visit> visits = visitService.findAll();

        assertNotNull(visits);
        assertFalse(visits.isEmpty());

        log.info("Cantidad de visitas encontradas: {}", visits.size());
    }

    @Test
    public void testUpdateVisit() {
        Visit visit = visitService.findById(1);

        assertNotNull(visit);

        visit.setDescription("Visita actualizada para control veterinario");
        visit.setVisitDate(LocalDate.of(2026, 6, 1));

        Visit updatedVisit = visitService.update(visit);

        assertEquals("Visita actualizada para control veterinario", updatedVisit.getDescription());
        assertEquals(LocalDate.of(2026, 6, 1), updatedVisit.getVisitDate());

        log.info("Visita actualizada: {}", updatedVisit);
    }

    @Test
    public void testDeleteVisit() {
        Visit visit = Visit.builder()
                .petId(1)
                .visitDate(LocalDate.of(2026, 5, 28))
                .description("Visita temporal para eliminar")
                .build();

        Visit createdVisit = visitService.create(visit);
        Integer id = createdVisit.getId();

        visitService.delete(id);

        Visit deletedVisit = visitService.findById(id);

        assertNull(deletedVisit);

        log.info("Visita eliminada con ID: {}", id);
    }

    @Test
    public void testFindVisitByPetId() {
        List<Visit> visits = visitService.findByPetId(1);

        assertNotNull(visits);

        log.info("Visitas encontradas por mascota: {}", visits);
    }
}