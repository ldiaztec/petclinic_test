package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
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
    public void testFindVisitById() {
        Integer ID = 1;

        Visit visit = null;
        try {
            visit = this.visitService.findById(ID);
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("" + visit);
        assertNotNull(visit);
        assertEquals(ID, visit.getId());
    }

    @Test
    public void testFindVisitByPetId() {
        Integer PET_ID = 1;
        int SIZE_EXPECTED = 1;

        var visits = this.visitService.findByPetId(PET_ID);
        log.info("" + visits);
        assertFalse(visits.isEmpty());
    }

    @Test
    public void testCreateVisit() {
        Integer PET_ID  = 1;
        Integer VET_ID  = 1;
        String  DESC    = "Consulta general";
        Double  COST    = 50.0;

        Visit visit = new Visit(null, PET_ID, VET_ID, LocalDate.now(), DESC, COST);
        Visit created = this.visitService.create(visit);

        log.info("VISIT CREATED: " + created);
        assertNotNull(created.getId());
        assertEquals(PET_ID, created.getPetId());
        assertEquals(DESC,   created.getDescription());
    }

    @Test
    public void testUpdateVisit() {
        String DESC    = "Vacunacion";
        String UP_DESC = "Vacunacion anual completa";

        Visit visit = new Visit(null, 1, 1, LocalDate.now(), DESC, 30.0);
        Visit created = this.visitService.create(visit);
        log.info("CREATED: " + created);

        created.setDescription(UP_DESC);
        Visit updated = this.visitService.update(created);
        log.info("UPDATED: " + updated);

        assertEquals(UP_DESC, updated.getDescription());
    }

    @Test
    public void testDeleteVisit() {
        Visit visit = new Visit(null, 1, 1, LocalDate.now(), "Control rutinario", 20.0);
        Visit created = this.visitService.create(visit);
        log.info("CREATED: " + created);

        try {
            this.visitService.delete(created.getId());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.visitService.findById(created.getId());
            assertTrue(false);
        } catch (VisitNotFoundException e) {
            assertTrue(true);
        }
    }
}