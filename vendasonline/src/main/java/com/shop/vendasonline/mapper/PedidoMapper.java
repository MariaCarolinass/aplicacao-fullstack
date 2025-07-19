package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.PedidoDTO;
import com.shop.vendasonline.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    
    @Mapping(source = "cliente.id", target = "clienteId")
    PedidoDTO toDto(Pedido entity);

    @Mapping(target = "cliente", ignore = true)
    Pedido toEntity(PedidoDTO dto);

}
