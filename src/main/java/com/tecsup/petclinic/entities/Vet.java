package com.tecsup.petclinic.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "vets") @Data
public class Vet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    public Vet() {}
    public Vet(String firstName, String lastName) { this.firstName = firstName; this.lastName = lastName; }
}