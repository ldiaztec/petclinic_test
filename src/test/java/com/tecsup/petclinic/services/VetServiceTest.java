package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
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
        Vet vet = Vet.builder()
                .firstName("Rosita")
                .lastName("Garcia")
                .email("rosita.garcia@petclinic.com")
                .phone("987654321")
                .active(true)
                .build();

        Vet createdVet = vetService.create(vet);

        assertNotNull(createdVet);
        assertNotNull(createdVet.getId());
        assertEquals("Rosita", createdVet.getFirstName());
        assertEquals("Garcia", createdVet.getLastName());

        log.info("Veterinario creado: {}", createdVet);
    }

    @Test
    public void testFindVetById() {
        Vet vet = vetService.findById(1);

        assertNotNull(vet);
        assertEquals("James", vet.getFirstName());

        log.info("Veterinario encontrado: {}", vet);
    }

    @Test
    public void testFindAllVets() {
        List<Vet> vets = vetService.findAll();

        assertNotNull(vets);
        assertFalse(vets.isEmpty());

        log.info("Cantidad de veterinarios encontrados: {}", vets.size());
    }

    @Test
    public void testUpdateVet() {
        Vet vet = vetService.findById(1);

        assertNotNull(vet);

        vet.setPhone("999888777");
        vet.setEmail("james.updated@petclinic.com");

        Vet updatedVet = vetService.update(vet);

        assertEquals("999888777", updatedVet.getPhone());
        assertEquals("james.updated@petclinic.com", updatedVet.getEmail());

        log.info("Veterinario actualizado: {}", updatedVet);
    }

    @Test
    public void testDeleteVet() {
        Vet vet = Vet.builder()
                .firstName("Temporal")
                .lastName("Delete")
                .email("temporal.delete@petclinic.com")
                .phone("900000000")
                .active(true)
                .build();

        Vet createdVet = vetService.create(vet);
        Integer id = createdVet.getId();

        vetService.delete(id);

        Vet deletedVet = vetService.findById(id);

        assertNull(deletedVet);

        log.info("Veterinario eliminado con ID: {}", id);
    }

    @Test
    public void testFindVetByLastName() {
        List<Vet> vets = vetService.findByLastName("Carter");

        assertNotNull(vets);
        assertFalse(vets.isEmpty());

        log.info("Veterinarios encontrados por apellido: {}", vets);
    }
}