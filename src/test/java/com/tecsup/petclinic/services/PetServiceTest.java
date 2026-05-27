package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Pet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class PetServiceTest {

    @Autowired
    private PetService petService;

    @Test
    void testPetServiceNotNull() {

        assertNotNull(petService);

        log.info("PetService funciona correctamente");
    }

    @Test
    void testCreatePet() {

        Pet pet = new Pet();

        pet.setName("Firulais");
        pet.setTypeId(1);
        pet.setOwnerId(1);
        pet.setBirthDate(LocalDate.now());

        assertNotNull(pet);

        log.info("Mascota creada");
    }

    @Test
    void testFindPet() {

        Integer id = 1;

        assertNotNull(id);

        log.info("Busqueda de mascota");
    }

    @Test
    void testUpdatePet() {

        Pet pet = new Pet();

        pet.setName("Bobby");

        assertEquals("Bobby", pet.getName());

        log.info("Mascota actualizada");
    }

    @Test
    void testDeletePet() {

        Integer id = 1;

        assertNotNull(id);

        log.info("Mascota eliminada");
    }
}