package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "vet_specialties")
@IdClass(VetSpecialtyId.class)
@Data
@NoArgsConstructor
public class VetSpecialty {

    @Id
    @Column(name = "vet_id")
    private Integer vetId;

    @Id
    @Column(name = "specialty_id")
    private Integer specialtyId;

    @Column(name = "certification_date")
    private LocalDate certificationDate;

    @Column(name = "years_experience")
    private Integer yearsExperience = 0;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "notes")
    private String notes;

    public VetSpecialty(Integer vetId, Integer specialtyId) {
        this.vetId = vetId;
        this.specialtyId = specialtyId;
    }
}
