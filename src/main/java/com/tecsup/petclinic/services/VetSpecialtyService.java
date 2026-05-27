package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetSpecialtyService {

    @Autowired private VetRepository vetRepository;
    @Autowired private SpecialtyRepository specialtyRepository;

    // 1. Asignar especialidad
    public Vet assignSpecialty(Long vetId, Long specialtyId) {
        Vet vet = vetRepository.findById(vetId).orElse(null);
        Specialty spec = specialtyRepository.findById(specialtyId).orElse(null);

        if (vet != null && spec != null) {
            vet.setSpecialty(spec); // Usamos tu método singular
            return vetRepository.save(vet);
        }
        return null;
    }

    // 2. Buscar especialidad por veterinario
    public Specialty getSpecialtiesByVet(Long vetId) {
        Vet vet = vetRepository.findById(vetId).orElse(null);
        return (vet != null) ? vet.getSpecialty() : null; // Retorna la especialidad asignada
    }

    // 3. Buscar veterinarios por especialidad
    public List<Vet> getVetsBySpecialty(Long specialtyId) {
        return vetRepository.findAll().stream()
                .filter(v -> v.getSpecialty() != null && v.getSpecialty().getId().equals(specialtyId))
                .collect(Collectors.toList());
    }

    // 4. Eliminar relación
    public Vet removeSpecialty(Long vetId, Long specialtyId) {
        Vet vet = vetRepository.findById(vetId).orElse(null);
        if (vet != null && vet.getSpecialty() != null && vet.getSpecialty().getId().equals(specialtyId)) {
            vet.setSpecialty(null); // La desvinculamos poniéndola en null
            return vetRepository.save(vet);
        }
        return null;
    }
}