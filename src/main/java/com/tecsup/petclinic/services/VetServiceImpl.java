package com.tecsup.petclinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;

@Service
@SuppressWarnings("null") 
public class VetServiceImpl implements VetService {
    
    @Autowired 
    private VetRepository vetRepository;
    
    @Override 
    public Vet create(Vet vet) { 
        return vetRepository.save(vet); 
    }
    
    @Override 
    public Vet update(Vet vet) { 
        return vetRepository.save(vet); 
    }
    
    @Override 
    public void delete(Long id) { 
        vetRepository.deleteById(id); 
    }
    
    @Override 
    public Vet findById(Long id) { 
        return vetRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")); 
    }
}