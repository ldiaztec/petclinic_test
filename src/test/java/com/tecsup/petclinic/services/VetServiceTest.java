package com.tecsup.petclinic.services;

import com.tecsup.petclinic.domain.*; // Importa todas las entidades (incluyendo Vet)
import com.tecsup.petclinic.service.*; // Importa todos los servicios (incluyendo VetService)
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2") 
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testCreateVet() {
        String FIRST_NAME = "James";
        String LAST_NAME = "Carter";

        Vet vet = Vet.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        Vet savedVet = vetService.create(vet);
        log.info("Vet creado: {}", savedVet);

        Assertions.assertNotNull(savedVet.getId());
        Assertions.assertEquals(FIRST_NAME, savedVet.getFirstName());
        Assertions.assertEquals(LAST_NAME, savedVet.getLastName());
    }

    @Test
    public void testFindVetById() {
        Vet vet = Vet.builder().firstName("Helen").lastName("Leary").build();
        Vet savedVet = vetService.create(vet);

        Vet foundVet = vetService.findById(savedVet.getId());
        log.info("Vet encontrado: {}", foundVet);

        Assertions.assertEquals(savedVet.getId(), foundVet.getId());
        Assertions.assertEquals("Helen", foundVet.getFirstName());
    }

    @Test
    public void testUpdateVet() {
        Vet vet = Vet.builder().firstName("Linda").lastName("Douglas").build();
        Vet savedVet = vetService.create(vet);

        savedVet.setFirstName("Linda Maria");
        Vet updatedVet = vetService.update(savedVet);
        log.info("Vet actualizado: {}", updatedVet);

        Assertions.assertEquals("Linda Maria", updatedVet.getFirstName());
    }

    @Test
    public void testDeleteVet() {
        Vet vet = Vet.builder().firstName("Rafael").lastName("Ortega").build();
        Vet savedVet = vetService.create(vet);
        Integer id = savedVet.getId();

        vetService.delete(id);

        Assertions.assertThrows(RuntimeException.class, () -> {
            vetService.findById(id);
        });
        log.info("Vet con ID {} eliminado correctamente", id);
    }
}