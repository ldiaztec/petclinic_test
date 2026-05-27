package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    /**
     * 1. PRUEBA DE CREACIÓN (CREATE)
     */
    @Test
    public void testCreateType() {
        String NAME = "Bird";

        // Crear una nueva instancia de Type
        Type type = new Type();
        type.setName(NAME);

        // Guardar en la base de datos
        Type savedType = typeService.save(type);
        log.info("Tipo de mascota creado: {}", savedType);

        // Verificaciones
        assertNotNull(savedType.getId(), "El ID no debería ser nulo tras guardar");
        assertEquals(NAME, savedType.getName());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ / SEARCH)
     */
    @Test
    public void testFindTypeById() {
        // Creamos un tipo primero para garantizar un ID válido en las pruebas
        Type type = new Type();
        type.setName("Hamster");
        Type savedType = typeService.save(type);
        Integer idABuscar = savedType.getId();

        try {
            Type foundType = typeService.findById(idABuscar);
            log.info("Tipo de mascota encontrado: {}", foundType);

            assertNotNull(foundType, "El tipo de mascota con ID " + idABuscar + " debería existir");
            assertEquals(idABuscar, foundType.getId());
        } catch (Exception e) {
            fail("Error al buscar el tipo de mascota: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateType() {
        // 1. Creamos un tipo temporal
        Type type = new Type();
        type.setName("Snake");
        Type savedType = typeService.save(type);

        // 2. Modificamos su nombre
        String NUEVO_NOMBRE = "Reptile";
        savedType.setName(NUEVO_NOMBRE);

        // 3. Actualizamos en la BD
        Type updatedType = typeService.save(savedType);
        log.info("Tipo de mascota actualizado: {}", updatedType);

        // 4. Verificaciones
        assertEquals(savedType.getId(), updatedType.getId(), "El ID debe ser el mismo");
        assertEquals(NUEVO_NOMBRE, updatedType.getName(), "El nombre debió cambiar");
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteType() {
        // 1. Creamos un tipo rápido para borrarlo
        Type type = new Type();
        type.setName("TemporaryType");
        Type savedType = typeService.save(type);
        Integer id = savedType.getId();

        // 2. Ejecutamos la eliminación
        try {
            typeService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar el tipo de mascota: " + e.getMessage());
        }

        // 3. Intentamos buscarlo de nuevo para verificar
        try {
            Type deletedType = typeService.findById(id);
            assertNull(deletedType, "El tipo con ID " + id + " debería retornar null tras ser eliminado");
            log.info("Confirmado: El tipo de mascota fue eliminado correctamente.");
        } catch (Exception e) {
            log.info("Confirmado: El servicio lanzó una excepción esperada al buscar un tipo eliminado: {}", e.getMessage());
        }
    }
}