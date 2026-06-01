package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    /**
     * PRUEBA DE CREACIÓN
     */
    @Test
    @DisplayName("Registrar nuevo dueño")
    void createOwnerTest() {

        String NOMBRE = "Jonel";
        String APELLIDO = "Villanueva";

        Owner owner = new Owner();

        owner.setFirstName(NOMBRE);
        owner.setLastName(APELLIDO);
        owner.setAddress("Av. Primavera 450");
        owner.setCity("Arequipa");
        owner.setTelephone("987654321");

        Owner ownerGuardado = ownerService.save(owner);

        log.info("Dueño registrado: {}", ownerGuardado);

        assertAll(
                () -> assertNotNull(ownerGuardado.getId()),
                () -> assertEquals(NOMBRE, ownerGuardado.getFirstName()),
                () -> assertEquals(APELLIDO, ownerGuardado.getLastName())
        );
    }

    /**
     * PRUEBA DE BÚSQUEDA
     */
    @Test
    @DisplayName("Buscar dueño por ID")
    void findOwnerTest() {

        Integer idOwner = 1;

        try {

            Owner ownerEncontrado = ownerService.findById(idOwner);

            log.info("Dueño encontrado: {}", ownerEncontrado);

            assertNotNull(ownerEncontrado);
            assertEquals(idOwner, ownerEncontrado.getId());

        } catch (Exception e) {

            fail("Error al buscar dueño: " + e.getMessage());
        }
    }

    /**
     * PRUEBA DE ACTUALIZACIÓN
     */
    @Test
    @DisplayName("Actualizar información del dueño")
    void updateOwnerTest() {

        // Crear dueño temporal
        Owner owner = new Owner();

        owner.setFirstName("Maria");
        owner.setLastName("Lopez");
        owner.setAddress("Av. Central");

        Owner ownerGuardado = ownerService.save(owner);

        // Modificar datos
        ownerGuardado.setCity("Lima");
        ownerGuardado.setTelephone("955667788");

        // Actualizar información
        Owner ownerActualizado = ownerService.save(ownerGuardado);

        log.info("Dueño actualizado: {}", ownerActualizado);

        // Validaciones
        assertEquals("Lima", ownerActualizado.getCity());
        assertEquals("955667788", ownerActualizado.getTelephone());
    }

    /**
     * PRUEBA DE ELIMINACIÓN
     */
    @Test
    @DisplayName("Eliminar dueño")
    void deleteOwnerTest() {

        // Crear dueño temporal
        Owner owner = new Owner();

        owner.setFirstName("Temporal");
        owner.setLastName("Eliminar");

        Owner ownerGuardado = ownerService.save(owner);

        Integer ownerId = ownerGuardado.getId();

        // Eliminar dueño
        try {

            ownerService.deleteById(ownerId);

            log.info("Dueño eliminado correctamente");

        } catch (Exception e) {

            fail("Error al eliminar dueño: " + e.getMessage());
        }

        // Verificar eliminación
        assertThrows(Exception.class, () -> {

            ownerService.findById(ownerId);

        });
    }
}