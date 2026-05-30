package com.tecsup.petclinic.services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VetSpecialtyService {
    public void assignSpecialty(Integer vetId, Integer specialtyId) {}
    public List<Integer> findSpecialtiesByVet(Integer vetId) { return new ArrayList<>(); }
    public List<Integer> findVetsBySpecialty(Integer specialtyId) { return new ArrayList<>(); }
    public void removeRelation(Integer vetId, Integer specialtyId) {}
}