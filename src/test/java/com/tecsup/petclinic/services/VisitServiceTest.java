package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class VisitServiceTest {

    @Autowired
    VisitService visitService;

    @Test
    public void testCreateVisit() {
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVisitDate(new Date());
        visit.setDescription("Consulta de rutina");

        Visit saved = visitService.save(visit);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    public void testFindVisitById() {
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVisitDate(new Date());
        visit.setDescription("Control post-operatorio");
        Visit saved = visitService.save(visit);

        try {
            Visit found = visitService.findById(saved.getId());
            assertEquals("Control post-operatorio", found.getDescription());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateVisit() {
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVisitDate(new Date());
        visit.setDescription("Descripción original");
        Visit saved = visitService.save(visit);

        saved.setDescription("Descripción actualizada");
        visitService.save(saved);

        try {
            Visit updated = visitService.findById(saved.getId());
            assertEquals("Descripción actualizada", updated.getDescription());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteVisit() {
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVisitDate(new Date());
        visit.setDescription("Visita a eliminar");
        Visit saved = visitService.save(visit);
        int id = saved.getId();

        visitService.delete(saved);

        assertThrows(VisitNotFoundException.class, () -> {
            visitService.findById(id);
        });
    }
}