package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(VetServiceImpl.class)
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    public void testCreateVet() {
        entityManager.createNativeQuery(
            "ALTER TABLE vets ALTER COLUMN id RESTART WITH 100")
            .executeUpdate();

        Vet vet = new Vet(null, "David", "Solano",
                "david@clinic.com", "999888777", true);
        Vet saved = vetService.create(vet);
        assertNotNull(saved.getId());
        assertEquals(100, saved.getId());
        log.info("Vet creado: {}", saved);
    }

    @Test
    public void testFindVetById() {
        Vet found = vetService.findById(1);
        assertNotNull(found);
        assertEquals("James", found.getFirstName());
        assertEquals("Carter", found.getLastName());
        log.info("Vet encontrado: {}", found);
    }

    @Test
    public void testFindAllVets() {
        var vets = vetService.findAll();
        assertFalse(vets.isEmpty());
        assertEquals(6, vets.size());
        log.info("Total vets: {}", vets.size());
    }

    @Test
    public void testFindByLastName() {
        var vets = vetService.findByLastName("Leary");
        assertFalse(vets.isEmpty());
        assertEquals("Helen", vets.get(0).getFirstName());
        log.info("Vets encontrados por apellido: {}", vets.size());
    }

    @Test
    public void testUpdateVet() {
        Vet vet = vetService.findById(3);
        vet.setLastName("Douglas Updated");
        Vet updated = vetService.update(vet);
        assertEquals("Douglas Updated", updated.getLastName());
        log.info("Vet actualizado: {}", updated);
    }

    @Test
    public void testDeleteVet() {
        vetService.delete(6);
        assertNull(vetService.findById(6));
        log.info("Vet eliminado correctamente");
    }
}