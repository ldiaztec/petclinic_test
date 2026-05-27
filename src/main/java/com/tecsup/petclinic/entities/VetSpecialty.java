package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vet_specialties")
@IdClass(VetSpecialty.VetSpecialtyId.class) // Define la estructura de llave compuesta
public class VetSpecialty {

    @Id
    @Column(name = "vet_id")
    private Integer vetId;

    @Id
    @Column(name = "specialty_id")
    private Integer specialtyId;

    // Clase estática requerida por JPA para manejar la llave primaria compuesta
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VetSpecialtyId implements Serializable {
        private Integer vetId;
        private Integer specialtyId;
    }
}