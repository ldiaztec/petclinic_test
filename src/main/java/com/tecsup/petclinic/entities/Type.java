package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 80)
    private String name;
}