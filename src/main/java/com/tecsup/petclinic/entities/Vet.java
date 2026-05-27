package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    private Boolean active;
}