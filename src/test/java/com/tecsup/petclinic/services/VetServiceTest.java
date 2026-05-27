package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.entities.Vet;

@SpringBootTest
@Transactional
class VetServiceTest {

    @Autowired 
    private VetService vetService;

    @Test 
    void testCRUD() {
        // 1. Crear
        Vet nuevo = vetService.create(new Vet("Irving", "Chavez"));
        assertNotNull(nuevo.getId());

        // 2. Buscar
        Vet buscado = vetService.findById(nuevo.getId());
        assertEquals("Irving", buscado.getFirstName());

        // 3. Actualizar
        buscado.setLastName("Modificado");
        vetService.update(buscado);
        assertEquals("Modificado", vetService.findById(nuevo.getId()).getLastName());

        // 4. Borrar
        try {
            Long idBorrado = nuevo.getId();
            vetService.delete(idBorrado);
            Object exceptionCheck = assertThrows(Exception.class, () -> {
                vetService.findById(idBorrado); 
            });
            assertNotNull(exceptionCheck);

        } catch (Exception e) {
            fail("El flujo de borrado en VetService lanzó una excepción inesperada: " + e.getMessage());
        }
    }
}