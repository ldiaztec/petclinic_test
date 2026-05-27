package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    private String city;

    private String telephone;
}