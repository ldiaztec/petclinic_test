package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VetMapper {

    VetMapper INSTANCE = Mappers.getMapper(VetMapper.class);

    VetDTO mapToDto(Vet vet);

    Vet mapToEntity(VetDTO vetDTO);
}