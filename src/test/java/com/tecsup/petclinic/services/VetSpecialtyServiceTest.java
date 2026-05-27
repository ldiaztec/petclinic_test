package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Speciality;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.entities.VetSpecialty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Autowired
    private VetService vetService;

    @Autowired
    private SpecialityService specialityService;

    @Test
    public void testAssignSpecialtyToVet() {
        Vet vet = vetService.create(
                Vet.builder()
                        .firstName("Carlos")
                        .lastName("Ramirez")
                        .email("carlos.ramirez@petclinic.com")
                        .phone("987111222")
                        .active(true)
                        .build()
        );

        Speciality speciality = specialityService.create(
                Speciality.builder()
                        .name("dermatology")
                        .build()
        );

        VetSpecialty relation = vetSpecialtyService.assignSpecialtyToVet(
                vet.getId(),
                speciality.getId()
        );

        assertNotNull(relation);
        assertEquals(vet.getId(), relation.getVetId());
        assertEquals(speciality.getId(), relation.getSpecialtyId());

        log.info("Relación creada: {}", relation);
    }

    @Test
    public void testFindSpecialtiesByVet() {
        Vet vet = vetService.create(
                Vet.builder()
                        .firstName("Andrea")
                        .lastName("Lopez")
                        .email("andrea.lopez@petclinic.com")
                        .phone("987333444")
                        .active(true)
                        .build()
        );

        Speciality speciality = specialityService.create(
                Speciality.builder()
                        .name("neurology")
                        .build()
        );

        vetSpecialtyService.assignSpecialtyToVet(vet.getId(), speciality.getId());

        List<VetSpecialty> specialties = vetSpecialtyService.findSpecialtiesByVet(vet.getId());

        assertNotNull(specialties);
        assertFalse(specialties.isEmpty());

        log.info("Especialidades encontradas por veterinario: {}", specialties);
    }

    @Test
    public void testFindVetsBySpecialty() {
        Vet vet = vetService.create(
                Vet.builder()
                        .firstName("Luis")
                        .lastName("Torres")
                        .email("luis.torres@petclinic.com")
                        .phone("987555666")
                        .active(true)
                        .build()
        );

        Speciality speciality = specialityService.create(
                Speciality.builder()
                        .name("oncology")
                        .build()
        );

        vetSpecialtyService.assignSpecialtyToVet(vet.getId(), speciality.getId());

        List<VetSpecialty> vets = vetSpecialtyService.findVetsBySpecialty(speciality.getId());

        assertNotNull(vets);
        assertFalse(vets.isEmpty());

        log.info("Veterinarios encontrados por especialidad: {}", vets);
    }

    @Test
    public void testDeleteVetSpecialtyRelation() {
        Vet vet = vetService.create(
                Vet.builder()
                        .firstName("Temporal")
                        .lastName("Vet")
                        .email("temporal.vet@petclinic.com")
                        .phone("987777888")
                        .active(true)
                        .build()
        );

        Speciality speciality = specialityService.create(
                Speciality.builder()
                        .name("temporary-specialty")
                        .build()
        );

        vetSpecialtyService.assignSpecialtyToVet(vet.getId(), speciality.getId());

        vetSpecialtyService.deleteRelation(vet.getId(), speciality.getId());

        VetSpecialty deletedRelation = vetSpecialtyService.findByIds(
                vet.getId(),
                speciality.getId()
        );

        assertNull(deletedRelation);

        log.info("Relación eliminada entre vet {} y specialty {}", vet.getId(), speciality.getId());
    }
}