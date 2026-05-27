package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(OwnerServiceImpl.class)
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    public void testCreateOwner() {
        entityManager.createNativeQuery(
            "ALTER TABLE owners ALTER COLUMN id RESTART WITH 100")
            .executeUpdate();

        Owner owner = new Owner(null, "Daniel", "Solano",
                "Av. Lima 123", "Lima", "999888777");
        Owner saved = ownerService.create(owner);
        assertNotNull(saved.getId());
        assertEquals(100, saved.getId());
        log.info("Owner creado: {}", saved);
    }

    @Test
    public void testFindOwnerById() {
        // George Franklin ya existe con ID 1
        Owner found = ownerService.findById(1);
        assertNotNull(found);
        assertEquals("George", found.getFirstName());
        assertEquals("Franklin", found.getLastName());
        log.info("Owner encontrado: {}", found);
    }

    @Test
    public void testFindAllOwners() {
        var owners = ownerService.findAll();
        assertFalse(owners.isEmpty());
        assertEquals(10, owners.size()); // hay 10 owners en data.sql
        log.info("Total owners: {}", owners.size());
    }

    @Test
    public void testFindByLastName() {
        // Betty Davis ya existe
        var owners = ownerService.findByLastName("Davis");
        assertFalse(owners.isEmpty());
        log.info("Owners encontrados por apellido: {}", owners.size());
    }

    @Test
    public void testFindByCity() {
        // Hay 4 owners en Madison
        var owners = ownerService.findByCity("Madison");
        assertFalse(owners.isEmpty());
        log.info("Owners en Madison: {}", owners.size());
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = ownerService.findById(1);
        owner.setCity("Miraflores");
        Owner updated = ownerService.update(owner);
        assertEquals("Miraflores", updated.getCity());
        log.info("Owner actualizado: {}", updated);
    }

    @Test
    public void testDeleteOwner() {
        ownerService.delete(10);
        assertNull(ownerService.findById(10));
        log.info("Owner eliminado correctamente");
    }
}