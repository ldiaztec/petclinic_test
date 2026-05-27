package com.tecsup.petclinic.mappers;

import org.mapstruct.Mapper;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    Owner mapToEntity(OwnerDTO dto);
    OwnerDTO mapToDto(Owner entity);
}