    package com.tecsup.petclinic.services;

    import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
    import java.util.List;

    public interface VetSpecialtyService {

        VetSpecialtyDTO assignSpecialty(Integer vetId, Integer specialtyId);


        List<Integer> findSpecialtiesByVet(Integer vetId);


        List<Integer> findVetsBySpecialty(Integer specialtyId);

        
        void removeRelation(Integer vetId, Integer specialtyId);
    }