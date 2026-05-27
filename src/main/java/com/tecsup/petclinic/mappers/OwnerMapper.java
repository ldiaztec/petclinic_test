package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    OwnerDTO mapToDto(Owner owner);

    Owner mapToEntity(OwnerDTO ownerDTO);
}