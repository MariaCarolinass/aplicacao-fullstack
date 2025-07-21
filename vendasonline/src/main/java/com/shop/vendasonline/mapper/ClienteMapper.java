package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import com.shop.vendasonline.dto.ClienteDTO;
import com.shop.vendasonline.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDto(Cliente entity);

}