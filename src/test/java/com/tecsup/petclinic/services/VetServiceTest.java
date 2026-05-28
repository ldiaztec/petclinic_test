package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
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

        VetDTO vetDTO = VetDTO.builder()
                .firstName("Juan")
                .lastName("Perez")
                .build();

        VetDTO createdVet = vetService.create(vetDTO);

        assertNotNull(createdVet);
        assertNotNull(createdVet.getId());

        log.info("Veterinario creado: " + createdVet);
    }

    @Test
    public void testFindVetById() throws Exception {

        VetDTO vet = vetService.findById(1);

        assertNotNull(vet);

        log.info("Veterinario encontrado: " + vet);
    }

    @Test
    public void testUpdateVet() throws Exception {

        VetDTO vet = vetService.findById(1);

        vet.setFirstName("Carlos");

        VetDTO updatedVet = vetService.update(vet);

        assertEquals("Carlos", updatedVet.getFirstName());

        log.info("Veterinario actualizado: " + updatedVet);
    }

    @Test
    public void testDeleteVet() throws Exception {

        VetDTO vetDTO = VetDTO.builder()
                .firstName("Temporal")
                .lastName("Delete")
                .build();

        VetDTO createdVet = vetService.create(vetDTO);

        vetService.delete(createdVet.getId().intValue());

        Exception exception = assertThrows(Exception.class, () -> {
            vetService.findById(createdVet.getId().intValue());
        });

        log.info("Veterinario eliminado correctamente");
    }

    @Test
    public void testFindAllVets() {

        List<VetDTO> vets = vetService.findAll();

        assertNotNull(vets);
        assertTrue(vets.size() > 0);

        log.info("Total veterinarios: " + vets.size());
    }
}