package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class VetServiceTest {

    @Autowired
    VetService vetService;

    /** CREAR un veterinario y verificar que se le asignó un ID */
    @Test
    public void testCreateVet() {
        Vet vet = new Vet();
        vet.setFirstName("Carlos");
        vet.setLastName("Ramirez");

        Vet savedVet = vetService.save(vet);

        assertNotNull(savedVet);
        assertTrue(savedVet.getId() > 0,
            "El ID debe ser mayor que 0 después de guardar");
    }

    /** BUSCAR un veterinario por ID */
    @Test
    public void testFindVetById() {
        // Primero crea uno para tener un ID válido
        Vet vet = new Vet();
        vet.setFirstName("Ana");
        vet.setLastName("Torres");
        Vet saved = vetService.save(vet);

        // Ahora búscalo
        try {
            Vet found = vetService.findById(saved.getId());
            assertEquals("Ana", found.getFirstName(),
                "El nombre debe coincidir");
        } catch (VetNotFoundException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    /** ACTUALIZAR un veterinario */
    @Test
    public void testUpdateVet() {
        Vet vet = new Vet();
        vet.setFirstName("Luis");
        vet.setLastName("Mendoza");
        Vet saved = vetService.save(vet);

        // Modifica el nombre
        saved.setFirstName("LuisActualizado");
        vetService.save(saved);

        // Verifica el cambio
        try {
            Vet updated = vetService.findById(saved.getId());
            assertEquals("LuisActualizado", updated.getFirstName(),
                "El nombre actualizado debe persistir");
        } catch (VetNotFoundException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    /** ELIMINAR un veterinario y verificar que ya no existe */
    @Test
    public void testDeleteVet() {
        Vet vet = new Vet();
        vet.setFirstName("Pedro");
        vet.setLastName("Castillo");
        Vet saved = vetService.save(vet);
        int id = saved.getId();

        vetService.delete(saved);

        // Debe lanzar excepción porque ya no existe
        assertThrows(VetNotFoundException.class, () -> {
            vetService.findById(id);
        }, "Debe lanzar VetNotFoundException al buscar un vet eliminado");
    }
}