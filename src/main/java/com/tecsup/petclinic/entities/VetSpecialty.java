package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vet_specialties")
@IdClass(VetSpecialtyId.class)
@Data
public class VetSpecialty {

    @Id
    @Column(name = "vet_id")
    private Integer vetId;

    @Id
    @Column(name = "specialty_id")
    private Integer specialtyId;
}