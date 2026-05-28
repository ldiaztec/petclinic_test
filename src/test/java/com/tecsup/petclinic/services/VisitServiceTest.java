package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
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
    public void testCreateVisit() {

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(1)
                .visitDate(LocalDate.now())
                .description("Consulta general")
                .build();

        VisitDTO createdVisit = visitService.create(visitDTO);

        assertNotNull(createdVisit);
        assertNotNull(createdVisit.getId());

        log.info("Visit creada: " + createdVisit);
    }

    @Test
    public void testFindVisitById() {

        VisitDTO visit = visitService.findById(1);

        assertNotNull(visit);

        log.info("Visit encontrada: " + visit);
    }

    @Test
    public void testUpdateVisit() {

        VisitDTO visit = visitService.findById(1);

        visit.setDescription("Vacunacion");

        VisitDTO updatedVisit = visitService.update(visit);

        assertEquals("Vacunacion", updatedVisit.getDescription());

        log.info("Visit actualizada: " + updatedVisit);
    }

    @Test
    public void testFindAllVisits() {

        var visits = visitService.findAll();

        assertNotNull(visits);

        log.info("Total visits: " + visits.size());
    }

    @Test
    public void testDeleteVisit() {

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(1)
                .visitDate(LocalDate.now())
                .description("Temporal")
                .build();

        VisitDTO createdVisit = visitService.create(visitDTO);

        Integer id = createdVisit.getId();

        visitService.delete(id);

        VisitDTO deletedVisit = visitService.findById(id);

        assertNull(deletedVisit);

        log.info("Visit eliminada correctamente");
    }
}