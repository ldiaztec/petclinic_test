package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    /**
     * 1. PRUEBA DE CREACIÓN (CREATE)
     */
    @Test
    public void testCreateOwner() {
        String FIRST_NAME = "Eduardo";
        String LAST_NAME = "Rodriquez";
        String ADDRESS = "Av. Salaverry 123";
        String CITY = "Lima";
        String TELEPHONE = "987654321";

        // Crear una nueva instancia de Owner
        Owner owner = new Owner();
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        owner.setAddress(ADDRESS);
        owner.setCity(CITY);
        owner.setTelephone(TELEPHONE);

        // Guardar en la base de datos
        Owner savedOwner = ownerService.save(owner);
        log.info("Dueño creado: {}", savedOwner);

        // Verificaciones
        assertNotNull(savedOwner.getId(), "El ID no debería ser nulo tras guardar");
        assertEquals(FIRST_NAME, savedOwner.getFirstName());
        assertEquals(LAST_NAME, savedOwner.getLastName());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ / SEARCH)
     */
    @Test
    public void testFindOwnerById() {
        Integer ID_A_BUSCAR = 1; // Ajustado según los datos iniciales estándar del proyecto

        try {
            Owner owner = ownerService.findById(ID_A_BUSCAR);
            log.info("Dueño encontrado: {}", owner);

            assertNotNull(owner, "El dueño con ID " + ID_A_BUSCAR + " debería existir");
            assertEquals(ID_A_BUSCAR, owner.getId());
        } catch (Exception e) {
            fail("Error al buscar el dueño: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateOwner() {
        // 1. Creamos un dueño temporal
        Owner owner = new Owner();
        owner.setFirstName("Maria");
        owner.setLastName("Lopez");
        owner.setAddress("Calle Libertad 456");
        owner.setCity("Arequipa");
        owner.setTelephone("955123456");
        Owner savedOwner = ownerService.save(owner);

        // 2. Modificamos su ciudad y teléfono
        String NUEVA_CIUDAD = "Cusco";
        savedOwner.setCity(NUEVA_CIUDAD);

        // 3. Actualizamos en la BD
        Owner updatedOwner = ownerService.save(savedOwner);
        log.info("Dueño actualizado: {}", updatedOwner);

        // 4. Verificaciones
        assertEquals(savedOwner.getId(), updatedOwner.getId(), "El ID debe ser el mismo");
        assertEquals(NUEVA_CIUDAD, updatedOwner.getCity(), "La ciudad debió cambiar");
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteOwner() {
        // 1. Creamos un dueño rápido para borrarlo
        Owner owner = new Owner();
        owner.setFirstName("Owner a");
        owner.setLastName("Eliminar");
        owner.setAddress("Test S/N");
        owner.setCity("TestCity");
        owner.setTelephone("000000");
        Owner savedOwner = ownerService.save(owner);
        Integer id = savedOwner.getId();

        // 2. Ejecutamos la eliminación
        try {
            ownerService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar el dueño: " + e.getMessage());
        }

        // 3. Intentamos buscarlo de nuevo para verificar
        try {
            Owner deletedOwner = ownerService.findById(id);
            assertNull(deletedOwner, "El dueño con ID " + id + " debería retornar null tras ser eliminado");
            log.info("Confirmado: El dueño fue eliminado correctamente.");
        } catch (Exception e) {
            log.info("Confirmado: El servicio lanzó una excepción esperada al buscar un dueño eliminado: {}", e.getMessage());
        }
    }
}