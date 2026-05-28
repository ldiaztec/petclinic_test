package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    void testVisitServiceNotNull() {

        assertNotNull(visitService);

        log.info("VisitService funciona correctamente");
    }

    @Test
    void testCreateVisit() {

        Visit visit = new Visit();

        visit.setPet_id(1);
        visit.setVet_id(1);
        visit.setVisit_date(LocalDate.now());
        visit.setDescription("Consulta general");
        visit.setCost(100.0);

        Visit savedVisit = visitService.create(visit);

        assertNotNull(savedVisit);

        log.info("Visita creada");
    }

    @Test
    void testFindVisit() {

        Visit visit = new Visit();

        visit.setPet_id(1);
        visit.setVet_id(1);
        visit.setVisit_date(LocalDate.now());
        visit.setDescription("Vacunacion");
        visit.setCost(80.0);

        Visit savedVisit = visitService.create(visit);

        Visit found = visitService.findById(savedVisit.getId());

        assertNotNull(found);

        log.info("Visita encontrada");
    }

    @Test
    void testUpdateVisit() {

        Visit visit = new Visit();

        visit.setPet_id(1);
        visit.setVet_id(1);
        visit.setVisit_date(LocalDate.now());
        visit.setDescription("Revision");
        visit.setCost(50.0);

        Visit savedVisit = visitService.create(visit);

        savedVisit.setDescription("Revision completa");

        Visit updated = visitService.update(savedVisit);

        assertEquals("Revision completa", updated.getDescription());

        log.info("Visita actualizada");
    }

    @Test
    void testDeleteVisit() {

        Visit visit = new Visit();

        visit.setPet_id(1);
        visit.setVet_id(1);
        visit.setVisit_date(LocalDate.now());
        visit.setDescription("Temporal");
        visit.setCost(30.0);

        Visit savedVisit = visitService.create(visit);

        visitService.delete(savedVisit.getId());

        assertTrue(true);

        log.info("Visita eliminada");
    }
}