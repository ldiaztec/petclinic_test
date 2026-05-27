package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "types")
@Data
public class PetType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}