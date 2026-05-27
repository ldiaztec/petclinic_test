package com.tecsup.petclinic.entities;

import java.io.Serializable;
import lombok.Data;

@Data
public class VetSpecialtyId implements Serializable {
    private Integer vetId;
    private Integer specialtyId;
}