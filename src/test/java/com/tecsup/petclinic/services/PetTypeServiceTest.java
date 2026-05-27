package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exceptions.PetTypeNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class PetTypeServiceTest {

    @Autowired
    PetTypeService petTypeService;

    @Test
    public void testCreatePetType() {
        PetType type = new PetType();
        type.setName("Reptil");

        PetType saved = petTypeService.save(type);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    public void testFindPetTypeById() {
        PetType type = new PetType();
        type.setName("Ave");
        PetType saved = petTypeService.save(type);

        try {
            PetType found = petTypeService.findById(saved.getId());
            assertEquals("Ave", found.getName());
        } catch (PetTypeNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdatePetType() {
        PetType type = new PetType();
        type.setName("Pez");
        PetType saved = petTypeService.save(type);

        saved.setName("Pez Tropical");
        petTypeService.save(saved);

        try {
            PetType updated = petTypeService.findById(saved.getId());
            assertEquals("Pez Tropical", updated.getName());
        } catch (PetTypeNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeletePetType() {
        PetType type = new PetType();
        type.setName("Hamster");
        PetType saved = petTypeService.save(type);
        int id = saved.getId();

        petTypeService.delete(saved);

        assertThrows(PetTypeNotFoundException.class, () -> {
            petTypeService.findById(id);
        });
    }
}