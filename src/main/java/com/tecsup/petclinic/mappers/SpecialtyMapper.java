package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyDTO mapToDto(Specialty specialty);
    Specialty mapToEntity(SpecialtyDTO specialtyDTO);
}