package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "vet_specialties")
@IdClass(VetSpecialty.VetSpecialtyId.class) // Indicamos que usa una llave compuesta
@Data
public class VetSpecialty {

    @Id
    @Column(name = "vet_id")
    private Integer vetId;

    @Id
    @Column(name = "specialty_id")
    private Integer specialtyId;

    // --- Subclase interna que representa la clave compuesta requerida por JPA ---
    @Data
    public static class VetSpecialtyId implements Serializable {
        private Integer vetId;
        private Integer specialtyId;
    }
}