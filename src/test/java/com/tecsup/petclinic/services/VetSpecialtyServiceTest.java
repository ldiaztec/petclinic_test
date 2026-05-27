package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class VetSpecialtyServiceTest {

    @Autowired
    VetSpecialtyService vetSpecialtyService;

    /** Asignar una especialidad a un veterinario */
    @Test
    public void testAsignarEspecialidadAVet() {
        VetSpecialty vs = new VetSpecialty();
        vs.setVetId(1);
        vs.setSpecialtyId(1);

        VetSpecialty saved = vetSpecialtyService.save(vs);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    /** Buscar especialidades por veterinario */
    @Test
    public void testBuscarEspecialidadesPorVet() {
        VetSpecialty vs = new VetSpecialty();
        vs.setVetId(2);
        vs.setSpecialtyId(1);
        vetSpecialtyService.save(vs);

        List<VetSpecialty> result = vetSpecialtyService.findByVetId(2);

        assertFalse(result.isEmpty(),
            "Debe encontrar al menos una especialidad para el vet con id 2");
    }

    /** Buscar veterinarios por especialidad */
    @Test
    public void testBuscarVetsPorEspecialidad() {
        VetSpecialty vs = new VetSpecialty();
        vs.setVetId(1);
        vs.setSpecialtyId(3);
        vetSpecialtyService.save(vs);

        List<VetSpecialty> result = vetSpecialtyService.findBySpecialtyId(3);

        assertFalse(result.isEmpty(),
            "Debe encontrar al menos un vet con la especialidad id 3");
    }

    /** Eliminar relación entre vet y especialidad */
    @Test
    public void testEliminarRelacionVetSpecialty() {
        VetSpecialty vs = new VetSpecialty();
        vs.setVetId(3);
        vs.setSpecialtyId(2);
        VetSpecialty saved = vetSpecialtyService.save(vs);
        int id = saved.getId();

        vetSpecialtyService.delete(saved);

        assertThrows(VetSpecialtyNotFoundException.class, () -> {
            vetSpecialtyService.findById(id);
        });
    }
}