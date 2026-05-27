package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional 
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    // 1. PRUEBA DE CREACIÓN (CREATE)
    @Test
    public void testCreateOwner() {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setFirstName("Juan Carlos");
        ownerDTO.setLastName("Ochoa Díaz");
        ownerDTO.setAddress("Av. Cascanueces 123");
        ownerDTO.setCity("Lima");
        ownerDTO.setTelephone("987654321");

        OwnerDTO expectedOwner = ownerService.create(ownerDTO);
        log.info("Creado: {}", expectedOwner);

        assertNotNull(expectedOwner.getId(), "El ID no debería ser nulo");
        assertEquals("Juan Carlos", expectedOwner.getFirstName());
    }

    @Test
    public void testFindOwnerById() {
        Integer idEsperado = 1; // Asumiendo que el ID 1 existe en tu base de datos
        try {
            OwnerDTO owner = ownerService.findById(idEsperado);
            log.info("Encontrado: {}", owner);
            assertNotNull(owner);
            assertEquals(idEsperado, owner.getId());
        } catch (OwnerNotFoundException e) {
            fail("No se debió lanzar OwnerNotFoundException: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateOwner() {
        Integer idTarget = 1;
        try {
            OwnerDTO owner = ownerService.findById(idTarget);
            String nuevoTelefono = "999999999";
            owner.setTelephone(nuevoTelefono);

            OwnerDTO updatedOwner = ownerService.update(owner);
            log.info("Actualizado: {}", updatedOwner);

            assertEquals(nuevoTelefono, updatedOwner.getTelephone());
        } catch (OwnerNotFoundException e) {
            fail("Error al actualizar (Owner no encontrado): " + e.getMessage());
        }
    }

    // 4. PRUEBA DE ELIMINACIÓN (DELETE)
    @Test
    public void testDeleteOwner() {

        OwnerDTO nuevo = new OwnerDTO();
        nuevo.setFirstName("Eliminar");
        nuevo.setLastName("Test");
        
        OwnerDTO guardado = ownerService.create(nuevo);
        Integer idParaBorrar = guardado.getId();

        try {
            ownerService.delete(idParaBorrar);
            
            OwnerNotFoundException ex = assertThrows(OwnerNotFoundException.class, () -> {
                ownerService.findById(idParaBorrar);
            });
            assertNotNull(ex.getMessage());
            
        } catch (OwnerNotFoundException e) {
            fail("Falló el proceso de eliminación: " + e.getMessage());
        }
    }
}