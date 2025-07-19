package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.ClienteDTO;
import com.shop.vendasonline.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDto(Cliente entity);

    @Mapping(target = "pedidos", ignore = true)
    Cliente toEntity(ClienteDTO dto);

}