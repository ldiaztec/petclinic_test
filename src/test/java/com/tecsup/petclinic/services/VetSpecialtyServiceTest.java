package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testFindVetSpecialtyById() {
        Integer VET_ID       = 1;
        Integer SPECIALTY_ID = 1;

        VetSpecialty vs = null;
        try {
            vs = this.vetSpecialtyService.findById(VET_ID, SPECIALTY_ID);
        } catch (VetSpecialtyNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("" + vs);
        assertNotNull(vs);
        assertEquals(VET_ID,       vs.getVetId());
        assertEquals(SPECIALTY_ID, vs.getSpecialtyId());
    }

    @Test
    public void testFindByVetId() {
        Integer VET_ID       = 1;
        int     SIZE_EXPECTED = 1;

        var list = this.vetSpecialtyService.findByVetId(VET_ID);
        log.info("" + list);
        assertEquals(SIZE_EXPECTED, list.size());
    }

    @Test
    public void testAssignVetSpecialty() {
        Integer VET_ID       = 2;
        Integer SPECIALTY_ID = 3;

        VetSpecialty vs = new VetSpecialty(VET_ID, SPECIALTY_ID,
                LocalDate.now(), 3, false, "Nueva asignacion");
        VetSpecialty assigned = this.vetSpecialtyService.assign(vs);

        log.info("ASSIGNED: " + assigned);
        assertNotNull(assigned);
        assertEquals(VET_ID,       assigned.getVetId());
        assertEquals(SPECIALTY_ID, assigned.getSpecialtyId());
    }

    @Test
    public void testUpdateVetSpecialty() {
        Integer VET_ID       = 3;
        Integer SPECIALTY_ID = 2;

        VetSpecialty vs = new VetSpecialty(VET_ID, SPECIALTY_ID,
                LocalDate.now(), 1, false, "inicial");
        this.vetSpecialtyService.assign(vs);

        vs.setNotes("actualizado");
        vs.setYearsExperience(5);
        VetSpecialty updated = this.vetSpecialtyService.update(vs);
        log.info("UPDATED: " + updated);

        assertEquals("actualizado", updated.getNotes());
        assertEquals(5, updated.getYearsExperience());
    }

    @Test
    public void testDeleteVetSpecialty() {
        Integer VET_ID       = 4;
        Integer SPECIALTY_ID = 1;

        VetSpecialty vs = new VetSpecialty(VET_ID, SPECIALTY_ID,
                LocalDate.now(), 2, false, "para eliminar");
        this.vetSpecialtyService.assign(vs);
        log.info("ASSIGNED: " + vs);

        try {
            this.vetSpecialtyService.delete(VET_ID, SPECIALTY_ID);
        } catch (VetSpecialtyNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.vetSpecialtyService.findById(VET_ID, SPECIALTY_ID);
            assertTrue(false);
        } catch (VetSpecialtyNotFoundException e) {
            assertTrue(true);
        }
    }
}