package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Data
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    private String description;

    @Column(name = "pet_id")
    private Integer petId;
}