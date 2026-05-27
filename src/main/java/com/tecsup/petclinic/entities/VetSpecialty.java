package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity(name = "vet_specialties")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class VetSpecialty {

    @EmbeddedId
    private VetSpecialtyId id;

    @Column(name = "certification_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate certificationDate;

    @Column(name = "years_experience")
    private Integer yearsExperience;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    private String notes;
}