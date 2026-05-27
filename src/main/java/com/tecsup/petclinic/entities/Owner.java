package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(length = 255)
    private String address;

    @Column(length = 80)
    private String city;

    @Column(length = 20)
    private String telephone;
}