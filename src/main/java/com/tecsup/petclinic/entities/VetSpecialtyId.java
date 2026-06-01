package com.tecsup.petclinic.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetSpecialtyId implements Serializable {
    private Integer vetId;
    private Integer specialtyId;
}
