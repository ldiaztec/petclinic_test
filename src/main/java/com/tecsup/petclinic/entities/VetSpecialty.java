package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vet_specialties")
@Data
public class VetSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vet_id")
    private Integer vetId;

    @Column(name = "specialty_id")
    private Integer specialtyId;
}