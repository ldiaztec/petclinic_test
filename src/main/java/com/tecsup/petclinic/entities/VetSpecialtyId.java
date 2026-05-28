package com.tecsup.petclinic.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class VetSpecialtyId implements Serializable {

    private Integer vetId;
    private Integer specialtyId;
}