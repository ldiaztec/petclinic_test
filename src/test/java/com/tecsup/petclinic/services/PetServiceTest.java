package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetDTO;
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
    public void testCreatePet() {

        PetDTO petDTO = PetDTO.builder()
                .name("Firulais")
                .typeId(1)
                .ownerId(1)
                .birthDate(LocalDate.now())
                .build();

        PetDTO createdPet = petService.create(petDTO);

        assertNotNull(createdPet);
        assertNotNull(createdPet.getId());

        log.info("Mascota creada: " + createdPet);

    }

    @Test
    public void testFindPetById() throws Exception {

        PetDTO pet = petService.findById(1);

        assertNotNull(pet);

        log.info("Mascota encontrada: " + pet);

    }

    @Test
    public void testUpdatePet() throws Exception {

        PetDTO pet = petService.findById(1);

        pet.setName("Bobby");

        PetDTO updatedPet = petService.update(pet);

        assertNotNull(updatedPet);

        assertEquals("Bobby", updatedPet.getName());

        log.info("Mascota actualizada: " + updatedPet);

    }

    @Test
    public void testDeletePet() throws Exception {

        PetDTO petDTO = PetDTO.builder()
            .name("Temporal")
            .typeId(1)
            .ownerId(1)
            .birthDate(LocalDate.now())
            .build();

        PetDTO createdPet = petService.create(petDTO);

        Integer id = createdPet.getId();

        petService.delete(id);

        assertThrows(Exception.class, () -> {
        petService.findById(id);
       });

        log.info("Mascota eliminada correctamente");

    }

    @Test
    public void testFindAllPets() {

        var pets = petService.findAll();

        assertNotNull(pets);

        assertTrue(pets.size() > 0);

        log.info("Total mascotas: " + pets.size());

}

}