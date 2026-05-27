package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(PetTypeServiceImpl.class)
@Slf4j
public class PetTypeServiceTest {

    @Autowired
    private PetTypeService petTypeService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    public void testCreatePetType() {
        entityManager.createNativeQuery(
            "ALTER TABLE types ALTER COLUMN id RESTART WITH 100")
            .executeUpdate();

        PetType petType = new PetType(null, "dragon", "Mythical creature",
                true, "large", 100, "extreme");
        PetType saved = petTypeService.create(petType);
        assertNotNull(saved.getId());
        assertEquals(100, saved.getId());
        log.info("PetType creado: {}", saved);
    }

    @Test
    public void testFindPetTypeById() {
        // cat ya existe con ID 1
        PetType found = petTypeService.findById(1);
        assertNotNull(found);
        assertEquals("cat", found.getName());
        log.info("PetType encontrado: {}", found);
    }

    @Test
    public void testFindAllPetTypes() {
        var types = petTypeService.findAll();
        assertFalse(types.isEmpty());
        assertEquals(8, types.size()); // hay 8 en data.sql
        log.info("Total types: {}", types.size());
    }

    @Test
    public void testFindByName() {
        var types = petTypeService.findByName("dog");
        assertFalse(types.isEmpty());
        assertEquals("dog", types.get(0).getName());
        log.info("PetTypes encontrados por nombre: {}", types.size());
    }

    @Test
    public void testFindByActive() {
        // snake (ID 6) es el único con active=false
        var types = petTypeService.findByActive(false);
        assertFalse(types.isEmpty());
        log.info("PetTypes inactivos: {}", types.size());
    }

    @Test
    public void testUpdatePetType() {
        PetType petType = petTypeService.findById(2);
        petType.setDescription("Domestic canine updated");
        PetType updated = petTypeService.update(petType);
        assertEquals("Domestic canine updated", updated.getDescription());
        log.info("PetType actualizado: {}", updated);
    }

    @Test
    public void testDeletePetType() {
        // snake ID 6 no tiene foreign keys activas
        petTypeService.delete(6);
        assertNull(petTypeService.findById(6));
        log.info("PetType eliminado correctamente");
    }
}