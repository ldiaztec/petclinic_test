package com.tecsup.petclinic.entities;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetSpecialtyId implements Serializable {
    private Integer vetId;
    private Integer specialtyId;
}