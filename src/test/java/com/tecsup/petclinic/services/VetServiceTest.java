package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    // Prueba de creación de veterinario
    @Test
    public void testCreateVet() {
        String VET_NAME = "Dr. Alberto Rosas";
        Specialty specialty = new Specialty();
        specialty.setId(1L);

        Vet vet = new Vet(VET_NAME, specialty);

        try {
            Vet createdVet = vetService.create(vet);
            log.info("Veterinario registrado con éxito: " + createdVet);
            assertNotNull(createdVet.getId(), "El ID no debería ser nulo");
            assertEquals(VET_NAME, createdVet.getName());
        } catch (Exception e) {
            fail("Error al crear el veterinario: " + e.getMessage());
        }
    }

    // Prueba de búsqueda por ID
    @Test
    public void testFindVetById() {
        Long ID_A_BUSCAR = 1L;

        try {
            Vet vet = vetService.findById(ID_A_BUSCAR);
            log.info("Veterinario encontrado: " + vet);

            assertNotNull(vet, "El veterinario no debería ser nulo");
            assertEquals(ID_A_BUSCAR, vet.getId());
        } catch (Exception e) {
            fail("Error al buscar el veterinario: " + e.getMessage());
        }
    }

    // Prueba de actualización
    @Test
    public void testUpdateVet() {
        Long ID_A_MODIFICAR = 1L;
        String NUEVO_NOMBRE = "Dr. James Modificado";

        try {
            Vet vetExistente = vetService.findById(ID_A_MODIFICAR);
            assertNotNull(vetExistente, "El veterinario a modificar debe existir");

            vetExistente.setName(NUEVO_NOMBRE);
            Vet vetActualizado = vetService.update(vetExistente);
            log.info("Veterinario actualizado con éxito: " + vetActualizado);

            assertEquals(NUEVO_NOMBRE, vetActualizado.getName());
        } catch (Exception e) {
            fail("Error al actualizar el veterinario: " + e.getMessage());
        }
    }

    // Prueba de eliminación
    @Test
    public void testDeleteVet() {
        Vet vetParaBorrar = new Vet();
        vetParaBorrar.setName("Dr. Temporal");

        try {
            Vet creado = vetService.create(vetParaBorrar);
            Long idCreado = creado.getId();
            log.info("Veterinario temporal creado con ID: " + idCreado);

            vetService.delete(idCreado);
            log.info("Veterinario con ID " + idCreado + " eliminado correctamente.");

            try {
                Vet buscado = vetService.findById(idCreado);
                assertNull(buscado, "El veterinario debería ser nulo tras eliminarlo");
            } catch (Exception e) {
                log.info("Confirmado: No se pudo encontrar el veterinario porque fue eliminado.");
            }

        } catch (Exception e) {
            fail("Error en el proceso de eliminación: " + e.getMessage());
        }
    }
}