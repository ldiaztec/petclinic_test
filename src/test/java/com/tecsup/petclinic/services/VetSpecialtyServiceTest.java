package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;

import com.tecsup.petclinic.repositories.SpecialtyRepository;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;

import org.junit.jupiter.api.Test; // Permite crear pruebas unitarias
import org.springframework.beans.factory.annotation.Autowired; // Inyecta objetos automáticamente
import org.springframework.boot.test.context.SpringBootTest; // Levanta el entorno de Spring Boot

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Inicia el contexto completo de Spring
public class VetSpecialtyServiceTest {

    @Autowired // Inyecta el repositorio de veterinarios
    private VetRepository vetRepository;

    @Autowired // Inyecta el repositorio de especialidades
    private SpecialtyRepository specialtyRepository;

    @Autowired // Inyecta el repositorio de la tabla intermedia
    private VetSpecialtyRepository vetSpecialtyRepository;

    @Test // Prueba de asignación de especialidad a veterinario
    public void testAssignSpecialtyToVet() {

        // Crear veterinario
        Vet vet = new Vet();
        vet.setFirstName("Juan");
        vet.setLastName("Perez");

        // Guardar veterinario
        Vet savedVet = vetRepository.save(vet);

        // Crear especialidad
        Specialty specialty = new Specialty();
        specialty.setName("Cirugía");

        // Guardar especialidad
        Specialty savedSpecialty =
                specialtyRepository.save(specialty);

        // Crear relación entre veterinario y especialidad
        VetSpecialty vetSpecialty = new VetSpecialty();

        // Asignar ID del veterinario
        vetSpecialty.setVetId(savedVet.getId());

        // Asignar ID de la especialidad
        vetSpecialty.setSpecialtyId(savedSpecialty.getId());

        // Guardar relación
        VetSpecialty savedRelation =
                vetSpecialtyRepository.save(vetSpecialty);

        // Verifica que la relación se guardó
        assertNotNull(savedRelation);
    }

    @Test // Prueba de búsqueda de especialidad por veterinario
    public void testFindSpecialtyByVet() {

        // Crear veterinario
        Vet vet = new Vet();
        vet.setFirstName("Carlos");
        vet.setLastName("Lopez");

        Vet savedVet = vetRepository.save(vet);

        // Crear especialidad
        Specialty specialty = new Specialty();
        specialty.setName("Dermatología");

        Specialty savedSpecialty =
                specialtyRepository.save(specialty);

        // Crear relación
        VetSpecialty vetSpecialty = new VetSpecialty();
        vetSpecialty.setVetId(savedVet.getId());
        vetSpecialty.setSpecialtyId(savedSpecialty.getId());

        // Guardar relación
        vetSpecialtyRepository.save(vetSpecialty);

        // Crear clave compuesta
        VetSpecialtyId id = new VetSpecialtyId();
        id.setVetId(savedVet.getId());
        id.setSpecialtyId(savedSpecialty.getId());

        // Buscar relación
        Optional<VetSpecialty> found =
                vetSpecialtyRepository.findById(id);

        // Verifica que exista
        assertTrue(found.isPresent());
    }

    @Test // Prueba de búsqueda de veterinario por especialidad
    public void testFindVetBySpecialty() {

        // Crear veterinario
        Vet vet = new Vet();
        vet.setFirstName("Luis");
        vet.setLastName("Garcia");

        Vet savedVet = vetRepository.save(vet);

        // Crear especialidad
        Specialty specialty = new Specialty();
        specialty.setName("Odontología");

        Specialty savedSpecialty =
                specialtyRepository.save(specialty);

        // Crear relación
        VetSpecialty vetSpecialty = new VetSpecialty();
        vetSpecialty.setVetId(savedVet.getId());
        vetSpecialty.setSpecialtyId(savedSpecialty.getId());

        // Guardar relación
        vetSpecialtyRepository.save(vetSpecialty);

        // Crear clave compuesta
        VetSpecialtyId id = new VetSpecialtyId();
        id.setVetId(savedVet.getId());
        id.setSpecialtyId(savedSpecialty.getId());

        // Buscar relación
        Optional<VetSpecialty> found =
                vetSpecialtyRepository.findById(id);

        // Verifica que el specialtyId sea correcto
        assertEquals(
                savedSpecialty.getId(),
                found.get().getSpecialtyId()
        );
    }

    @Test // Prueba de eliminación de relación
    public void testDeleteVetSpecialty() {

        // Crear veterinario
        Vet vet = new Vet();
        vet.setFirstName("Ana");
        vet.setLastName("Torres");

        Vet savedVet = vetRepository.save(vet);

        // Crear especialidad
        Specialty specialty = new Specialty();
        specialty.setName("Cardiología");

        Specialty savedSpecialty =
                specialtyRepository.save(specialty);

        // Crear relación
        VetSpecialty vetSpecialty = new VetSpecialty();
        vetSpecialty.setVetId(savedVet.getId());
        vetSpecialty.setSpecialtyId(savedSpecialty.getId());

        // Guardar relación
        vetSpecialtyRepository.save(vetSpecialty);

        // Eliminar relación
        vetSpecialtyRepository.delete(vetSpecialty);

        // Crear clave compuesta
        VetSpecialtyId id = new VetSpecialtyId();
        id.setVetId(savedVet.getId());
        id.setSpecialtyId(savedSpecialty.getId());

        // Buscar nuevamente
        Optional<VetSpecialty> deleted =
                vetSpecialtyRepository.findById(id);

        // Verifica que ya no exista
        assertFalse(deleted.isPresent());
    }
}