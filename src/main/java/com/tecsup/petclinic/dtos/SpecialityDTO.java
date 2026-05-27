package com.tecsup.petclinic.dtos;

import lombok.Data;

@Data
public class SpecialityDTO {
    private Integer id;
    private String name;
    private String office;
    private Integer h_open;
    private Integer h_close;
}