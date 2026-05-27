package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(VetSpecialtyServiceImpl.class)
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testCreateVetSpecialty() {
        // vet_id=1 + specialty_id=1 no existe en data.sql
        VetSpecialtyId id = new VetSpecialtyId(1, 1);
        VetSpecialty vs = new VetSpecialty(id,
                LocalDate.of(2020, 1, 15), 4, true, "Nueva asignacion");
        VetSpecialty saved = vetSpecialtyService.create(vs);
        assertNotNull(saved.getId());
        assertEquals(1, saved.getId().getVetId());
        assertEquals(1, saved.getId().getSpecialtyId());
        log.info("VetSpecialty creada: {}", saved);
    }

    @Test
    public void testFindVetSpecialtyById() {
        // vet_id=2, specialty_id=1 ya existe en data.sql
        VetSpecialtyId id = new VetSpecialtyId(2, 1);
        VetSpecialty found = vetSpecialtyService.findById(id);
        assertNotNull(found);
        assertEquals(2, found.getId().getVetId());
        log.info("VetSpecialty encontrada: {}", found);
    }

    @Test
    public void testFindAllVetSpecialties() {
        var list = vetSpecialtyService.findAll();
        assertFalse(list.isEmpty());
        assertEquals(5, list.size()); // hay 5 en data.sql
        log.info("Total vet_specialties: {}", list.size());
    }

    @Test
    public void testFindByVetId() {
        // vet_id=3 tiene 2 especialidades en data.sql
        var list = vetSpecialtyService.findByVetId(3);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        log.info("Especialidades del vet 3: {}", list.size());
    }

    @Test
    public void testFindBySpecialtyId() {
        // specialty_id=1 tiene 2 vets en data.sql
        var list = vetSpecialtyService.findBySpecialtyId(1);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        log.info("Vets con specialty 1: {}", list.size());
    }

    @Test
    public void testUpdateVetSpecialty() {
        VetSpecialtyId id = new VetSpecialtyId(2, 1);
        VetSpecialty vs = vetSpecialtyService.findById(id);
        vs.setYearsExperience(10);
        VetSpecialty updated = vetSpecialtyService.update(vs);
        assertEquals(10, updated.getYearsExperience());
        log.info("VetSpecialty actualizada: {}", updated);
    }

    @Test
    public void testDeleteVetSpecialty() {
        VetSpecialtyId id = new VetSpecialtyId(5, 1);
        vetSpecialtyService.delete(id);
        assertNull(vetSpecialtyService.findById(id));
        log.info("VetSpecialty eliminada correctamente");
    }
}