package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VetServiceTest {
    @Autowired private VetService vetService;

    @Test void testCRUD() {
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
        vetService.delete(nuevo.getId());
        assertThrows(RuntimeException.class, () -> vetService.findById(nuevo.getId()));
    }
}