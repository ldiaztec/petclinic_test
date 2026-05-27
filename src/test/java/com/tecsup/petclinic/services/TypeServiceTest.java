package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Slf4j
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    // 1. Prueba de Creación
    @Test
    public void testCreateType() {
        Type type = new Type();
        type.setName("Hámster");

        try {
            Type created = typeService.create(type);
            log.info("Tipo de mascota registrado con éxito: " + created);

            assertNotNull(created.getId(), "El ID no debería ser nulo");
            assertEquals("Hámster", created.getName());
        } catch (Exception e) {
            fail("Error al crear el tipo de mascota: " + e.getMessage());
        }
    }

    // 2. Prueba de Búsqueda por ID
    @Test
    public void testFindTypeById() {
        Long ID_A_BUSCAR = 1L; // Normalmente el ID 1 es "cat" o "dog" en PetClinic

        try {
            Type type = typeService.findById(ID_A_BUSCAR);
            log.info("Tipo de mascota encontrado: " + type);

            assertNotNull(type, "El tipo de mascota no debería ser nulo");
            assertEquals(ID_A_BUSCAR, type.getId());
        } catch (Exception e) {
            fail("Error al buscar el tipo de mascota: " + e.getMessage());
        }
    }

    // 3. Prueba de Actualización
    @Test
    public void testUpdateType() {
        Long ID_A_MODIFICAR = 1L;
        String NUEVO_NOMBRE = "Gato Persa";

        try {
            Type existente = typeService.findById(ID_A_MODIFICAR);
            assertNotNull(existente, "El tipo de mascota a modificar debe existir");

            existente.setName(NUEVO_NOMBRE);
            Type actualizado = typeService.update(existente);
            log.info("Tipo actualizado con éxito: " + actualizado);

            assertEquals(NUEVO_NOMBRE, actualizado.getName());
        } catch (Exception e) {
            fail("Error al actualizar el tipo de mascota: " + e.getMessage());
        }
    }

    // 4. Prueba de Eliminación
    @Test
    public void testDeleteType() {
        Type temporal = new Type();
        temporal.setName("Tipo Temporal");

        try {
            Type creado = typeService.create(temporal);
            Long idCreado = creado.getId();
            log.info("Tipo temporal creado con ID: " + idCreado);

            typeService.delete(idCreado);
            log.info("Tipo con ID " + idCreado + " eliminado correctamente.");

            try {
                Type buscado = typeService.findById(idCreado);
                assertNull(buscado, "El tipo debería ser nulo tras eliminarlo");
            } catch (Exception e) {
                log.info("Confirmado: No se pudo encontrar el tipo porque fue eliminado con éxito.");
            }

        } catch (Exception e) {
            fail("Error en el proceso de eliminación: " + e.getMessage());
        }
    }
}
