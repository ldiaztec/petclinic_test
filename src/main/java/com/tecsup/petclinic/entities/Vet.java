package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "vets")
@Data
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String lastName = "";

    // ¡NUEVO AJUSTE! Le indicamos a Hibernate que use la tabla intermedia 'vet_specialties'
    @ManyToOne
    @JoinTable(
            name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Specialty specialty;

    public Vet() {
    }

    public Vet(String name, Specialty specialty) {
        this.name = name;
        this.specialty = specialty;
    }
}