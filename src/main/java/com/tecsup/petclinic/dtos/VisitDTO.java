package com.tecsup.petclinic.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class VisitDTO {
    private Integer id;
    private Integer petId;
    private Integer vetId;
    private LocalDate visitDate;
    private String description;
    private BigDecimal cost;
}