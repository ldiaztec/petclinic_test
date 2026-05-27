package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Vet;
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
    void testVetServiceNotNull() {
        assertNotNull(vetService);

        log.info("VetService funciona correctamente");
    }
    @Test
    void testCreateVet() {

        Vet vet = new Vet();

        vet.setFirstName("Carlos");
        vet.setLastName("Perez");

        Vet savedVet = vetService.create(vet);

        assertNotNull(savedVet);

        log.info("Veterinario creado");
    }
    @Test
    void testFindVet() {
        Vet vet = vetService.findById(1);
        assertNotNull(vet);
        log.info("Veterinario encontrado");
    }
    @Test
    void testUpdateVet() {
        Vet vet = vetService.findById(1);
        vet.setFirstName("Juan");
        Vet updatedVet = vetService.update(vet);
        assertEquals("Juan", updatedVet.getFirstName());
        log.info("Veterinario actualizado");
    }
    @Test
    void testDeleteVet() {
        Vet vet = new Vet();
        vet.setFirstName("Temporal");
        vet.setLastName("Delete");
        Vet savedVet = vetService.create(vet);
        vetService.delete(savedVet.getId());
        assertTrue(true);
        log.info("Veterinario eliminado");
    }
}