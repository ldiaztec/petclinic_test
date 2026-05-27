package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testCreateVet() {
        VetDTO vetDTO = VetDTO.builder()
                .firstName("Carlos")
                .lastName("Mendoza")
                .email("carlos@gmail.com")
                .phone("999888777")
                .active(true)
                .build();

        VetDTO expectedVet = vetService.create(vetDTO);
        log.info("Veterinario creado con éxito: {}", expectedVet);

        assertNotNull(expectedVet.getId()); 
        assertEquals("Carlos", expectedVet.getFirstName());
        assertEquals("carlos@gmail.com", expectedVet.getEmail());
    }

    @Test
    public void testFindVetById() {
        VetDTO temp = vetService.create(VetDTO.builder()
                .firstName("James")
                .lastName("Carter")
                .email("james@gmail.com")
                .phone("111222333")
                .active(true)
                .build());
        
        try {
            VetDTO vetFound = vetService.findById(temp.getId());
            log.info("Veterinario encontrado por ID: {}", vetFound);
            
            assertNotNull(vetFound);
            assertEquals("James", vetFound.getFirstName());
        } catch (VetNotFoundException e) {
            fail("No debió fallar la búsqueda: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateVet() {
        VetDTO temp = vetService.create(VetDTO.builder()
                .firstName("Helen")
                .lastName("Leary")
                .email("helen@gmail.com")
                .phone("444555666")
                .active(true)
                .build());

        try {
            temp.setFirstName("Helen Editada");
            temp.setEmail("helen_nueva@gmail.com");

            VetDTO updatedVet = vetService.update(temp);
            log.info("Veterinario modificado con éxito: {}", updatedVet);

            assertEquals("Helen Editada", updatedVet.getFirstName());
            assertEquals("helen_nueva@gmail.com", updatedVet.getEmail());
        } catch (VetNotFoundException e) {
            fail("No debió fallar la actualización: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteVet() {
        VetDTO temp = vetService.create(VetDTO.builder()
                .firstName("Veterinario")
                .lastName("A Borrar")
                .email("borrar@gmail.com")
                .phone("000000")
                .active(true)
                .build());
        
        Long idToDelete = temp.getId();

        try {
            vetService.delete(idToDelete);
            log.info("Veterinario con ID {} eliminado correctamente", idToDelete);
        } catch (VetNotFoundException e) {
            fail("Falló la eliminación: " + e.getMessage());
        }

        assertThrows(VetNotFoundException.class, () -> {
            vetService.findById(idToDelete);
        });
    }
}