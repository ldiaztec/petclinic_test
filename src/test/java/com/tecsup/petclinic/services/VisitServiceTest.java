package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.PetRepository;
import com.tecsup.petclinic.repositories.VisitRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PetRepository petRepository;

    @Test
    public void testCreateVisit() {

        Pet pet = new Pet();
        pet.setName("Firulais");
        pet.setTypeId(1);
        pet.setOwnerId(1);
        pet.setBirthDate(LocalDate.now());

        Pet savedPet = petRepository.save(pet);

        Visit visit = new Visit();
        visit.setDescription("Consulta");
        visit.setVisitDate(LocalDate.now());
        visit.setPet(savedPet);

        Visit savedVisit = visitRepository.save(visit);

        assertNotNull(savedVisit.getId());
    }

    @Test
    public void testFindVisit() {

        Pet pet = new Pet();
        pet.setName("Max");
        pet.setTypeId(1);
        pet.setOwnerId(1);
        pet.setBirthDate(LocalDate.now());

        Pet savedPet = petRepository.save(pet);

        Visit visit = new Visit();
        visit.setDescription("Vacuna");
        visit.setVisitDate(LocalDate.now());
        visit.setPet(savedPet);

        Visit savedVisit = visitRepository.save(visit);

        Optional<Visit> found =
                visitRepository.findById(savedVisit.getId());

        assertTrue(found.isPresent());
    }

    @Test
    public void testUpdateVisit() {

        Pet pet = new Pet();
        pet.setName("Rocky");
        pet.setTypeId(1);
        pet.setOwnerId(1);
        pet.setBirthDate(LocalDate.now());

        Pet savedPet = petRepository.save(pet);

        Visit visit = new Visit();
        visit.setDescription("Antes");
        visit.setVisitDate(LocalDate.now());
        visit.setPet(savedPet);

        Visit savedVisit = visitRepository.save(visit);

        savedVisit.setDescription("Después");

        Visit updatedVisit = visitRepository.save(savedVisit);

        assertEquals(
                "Después",
                updatedVisit.getDescription()
        );
    }

    @Test
    public void testDeleteVisit() {

        Pet pet = new Pet();
        pet.setName("Luna");
        pet.setTypeId(1);
        pet.setOwnerId(1);
        pet.setBirthDate(LocalDate.now());

        Pet savedPet = petRepository.save(pet);

        Visit visit = new Visit();
        visit.setDescription("Eliminar");
        visit.setVisitDate(LocalDate.now());
        visit.setPet(savedPet);

        Visit savedVisit = visitRepository.save(visit);

        Integer id = savedVisit.getId();

        visitRepository.deleteById(id);

        Optional<Visit> deleted =
                visitRepository.findById(id);

        assertFalse(deleted.isPresent());
    }
}