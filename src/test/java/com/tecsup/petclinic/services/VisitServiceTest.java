package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
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

        int PET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.of(2026, 5, 27);
        String DESCRIPTION = "Control general";

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(PET_ID)
                .visitDate(VISIT_DATE)
                .description(DESCRIPTION)
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);

        log.info("VISITA CREADA: {}", newVisitDTO);

        assertNotNull(newVisitDTO);
        assertNotNull(newVisitDTO.getId());
        assertEquals(PET_ID, newVisitDTO.getPetId());
        assertEquals(VISIT_DATE, newVisitDTO.getVisitDate());
        assertEquals(DESCRIPTION, newVisitDTO.getDescription());
    }

    @Test
    public void testUpdateVisit() {

        int PET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.of(2026, 5, 27);
        String DESCRIPTION = "Control inicial";

        LocalDate UPDATED_VISIT_DATE = LocalDate.of(2026, 6, 1);
        String UPDATED_DESCRIPTION = "Control actualizado";

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(PET_ID)
                .visitDate(VISIT_DATE)
                .description(DESCRIPTION)
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);

        newVisitDTO.setVisitDate(UPDATED_VISIT_DATE);
        newVisitDTO.setDescription(UPDATED_DESCRIPTION);

        VisitDTO updatedVisitDTO = this.visitService.update(newVisitDTO);

        log.info("VISITA ACTUALIZADA: {}", updatedVisitDTO);

        assertNotNull(updatedVisitDTO);
        assertEquals(newVisitDTO.getId(), updatedVisitDTO.getId());
        assertEquals(PET_ID, updatedVisitDTO.getPetId());
        assertEquals(UPDATED_VISIT_DATE, updatedVisitDTO.getVisitDate());
        assertEquals(UPDATED_DESCRIPTION, updatedVisitDTO.getDescription());
    }

    @Test
    public void testFindVisitById() {

        int PET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.of(2026, 5, 28);
        String DESCRIPTION = "Vacunación";

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(PET_ID)
                .visitDate(VISIT_DATE)
                .description(DESCRIPTION)
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);

        VisitDTO visitFound = null;

        try {
            visitFound = this.visitService.findById(newVisitDTO.getId());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("VISITA ENCONTRADA POR ID: {}", visitFound);

        assertNotNull(visitFound);
        assertEquals(newVisitDTO.getId(), visitFound.getId());
        assertEquals(PET_ID, visitFound.getPetId());
        assertEquals(VISIT_DATE, visitFound.getVisitDate());
        assertEquals(DESCRIPTION, visitFound.getDescription());
    }

    @Test
    public void testFindVisitByPetId() {

        int PET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.of(2026, 5, 29);
        String DESCRIPTION = "Revisión dental";

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(PET_ID)
                .visitDate(VISIT_DATE)
                .description(DESCRIPTION)
                .build();

        this.visitService.create(visitDTO);

        List<VisitDTO> visits = this.visitService.findByPetId(PET_ID);

        log.info("VISITAS ENCONTRADAS POR PET ID: {}", visits);

        assertNotNull(visits);
        assertTrue(visits.size() > 0);
    }

    @Test
    public void testDeleteVisit() {

        int PET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.of(2026, 5, 30);
        String DESCRIPTION = "Visita para eliminar";

        VisitDTO visitDTO = VisitDTO.builder()
                .petId(PET_ID)
                .visitDate(VISIT_DATE)
                .description(DESCRIPTION)
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);

        log.info("VISITA A ELIMINAR: {}", newVisitDTO);

        try {
            this.visitService.delete(newVisitDTO.getId());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(VisitNotFoundException.class, () -> {
            this.visitService.findById(newVisitDTO.getId());
        });
    }
}