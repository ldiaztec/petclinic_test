package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testCreateVet() {

        String FIRST_NAME = "Juan";
        String LAST_NAME = "Perez";

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);

        log.info("VETERINARIO CREADO: {}", newVetDTO);

        assertNotNull(newVetDTO);
        assertNotNull(newVetDTO.getId());
        assertEquals(FIRST_NAME, newVetDTO.getFirstName());
        assertEquals(LAST_NAME, newVetDTO.getLastName());
    }

    @Test
    public void testUpdateVet() {

        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Ramirez";

        String UPDATED_FIRST_NAME = "Carlos Alberto";
        String UPDATED_LAST_NAME = "Ramirez Diaz";

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);

        newVetDTO.setFirstName(UPDATED_FIRST_NAME);
        newVetDTO.setLastName(UPDATED_LAST_NAME);

        VetDTO updatedVetDTO = this.vetService.update(newVetDTO);

        log.info("VETERINARIO ACTUALIZADO: {}", updatedVetDTO);

        assertNotNull(updatedVetDTO);
        assertEquals(newVetDTO.getId(), updatedVetDTO.getId());
        assertEquals(UPDATED_FIRST_NAME, updatedVetDTO.getFirstName());
        assertEquals(UPDATED_LAST_NAME, updatedVetDTO.getLastName());
    }

    @Test
    public void testFindVetById() {

        String FIRST_NAME = "Luis";
        String LAST_NAME = "Torres";

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);

        VetDTO vetFound = null;

        try {
            vetFound = this.vetService.findById(newVetDTO.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("VETERINARIO ENCONTRADO POR ID: {}", vetFound);

        assertNotNull(vetFound);
        assertEquals(newVetDTO.getId(), vetFound.getId());
        assertEquals(FIRST_NAME, vetFound.getFirstName());
        assertEquals(LAST_NAME, vetFound.getLastName());
    }

    @Test
    public void testFindVetByFirstName() {

        String FIRST_NAME = "Maria";
        String LAST_NAME = "Lopez";

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        this.vetService.create(vetDTO);

        List<VetDTO> vets = this.vetService.findByFirstName(FIRST_NAME);

        log.info("VETERINARIOS ENCONTRADOS POR NOMBRE: {}", vets);

        assertNotNull(vets);
        assertTrue(vets.size() > 0);
    }

    @Test
    public void testDeleteVet() {

        String FIRST_NAME = "Pedro";
        String LAST_NAME = "Castillo";

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);

        log.info("VETERINARIO A ELIMINAR: {}", newVetDTO);

        try {
            this.vetService.delete(newVetDTO.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(VetNotFoundException.class, () -> {
            this.vetService.findById(newVetDTO.getId());
        });
    }
}