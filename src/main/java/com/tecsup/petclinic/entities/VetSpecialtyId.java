package com.tecsup.petclinic.entities;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetSpecialtyId implements Serializable {

    private Integer vetId;
    private Integer specialtyId;
}