package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /**
     * 1. PRUEBA DE CREACIÓN (CREATE)
     */
    @Test
    public void testCreateVet() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Mendoza";

        // Crear una nueva instancia de veterinario
        Vet vet = new Vet();
        vet.setFirstName(FIRST_NAME);
        vet.setLastName(LAST_NAME);

        // Guardar en la base de datos a través del servicio
        Vet savedVet = vetService.save(vet);
        log.info("Veterinario creado: {}", savedVet);

        // Verificar que se haya guardado correctamente
        assertNotNull(savedVet.getId(), "El ID no debería ser nulo tras guardar");
        assertEquals(FIRST_NAME, savedVet.getFirstName());
        assertEquals(LAST_NAME, savedVet.getLastName());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ / SEARCH)
     */
    @Test
    public void testFindVetById() {
        Integer ID_A_BUSCAR = 1; // Asumiendo que el script de datos iniciales inserta un ID 1

        try {
            Vet vet = vetService.findById(ID_A_BUSCAR);
            log.info("Veterinario encontrado: {}", vet);

            assertNotNull(vet, "El veterinario con ID " + ID_A_BUSCAR + " debería existir");
            assertEquals(ID_A_BUSCAR, vet.getId());
        } catch (Exception e) {
            fail("Error al buscar el veterinario: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateVet() {
        // 1. Creamos un veterinario temporal para actualizarlo
        Vet vet = new Vet();
        vet.setFirstName("Ana");
        vet.setLastName("Gomez");
        Vet savedVet = vetService.save(vet);

        // 2. Modificamos sus datos
        String NUEVO_APELLIDO = "Gomez Modificado";
        savedVet.setLastName(NUEVO_APELLIDO);

        // 3. Actualizamos en la BD
        Vet updatedVet = vetService.save(savedVet);
        log.info("Veterinario actualizado: {}", updatedVet);

        // 4. Verificaciones
        assertEquals(savedVet.getId(), updatedVet.getId(), "El ID debe ser el mismo");
        assertEquals(NUEVO_APELLIDO, updatedVet.getLastName(), "El apellido debió cambiar");
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteVet() {
        // 1. Creamos un veterinario rápido para borrarlo
        Vet vet = new Vet();
        vet.setFirstName("Eliminar");
        vet.setLastName("Test");
        Vet savedVet = vetService.save(vet);
        Integer id = savedVet.getId();

        // 2. Ejecutamos la eliminación
        try {
            vetService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar el veterinario: " + e.getMessage());
        }

        // 3. Intentamos buscarlo de nuevo para verificar la eliminación.
        // Captura cualquier excepción genérica si el servicio falla al no encontrarlo,
        // u optimiza el flujo si retorna un objeto nulo.
        try {
            Vet deletedVet = vetService.findById(id);
            assertNull(deletedVet, "El veterinario con ID " + id + " debería retornar null tras ser eliminado");
            log.info("Confirmado: El veterinario fue eliminado (retornó null).");
        } catch (Exception e) {
            // Si salta un error de "registro no encontrado", significa que el delete funcionó correctamente
            log.info("Confirmado: El servicio lanzó una excepción esperada al buscar un veterinario eliminado: {}", e.getMessage());
        }
    }
}