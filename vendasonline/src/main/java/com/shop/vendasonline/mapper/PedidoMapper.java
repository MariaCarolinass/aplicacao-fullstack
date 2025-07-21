package com.shop.vendasonline.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.shop.vendasonline.dto.PedidoDTO;
import com.shop.vendasonline.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(target = "clienteNome", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    PedidoDTO toDto(Pedido entity);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    @Mapping(target = "venda", ignore = true)
    Pedido toEntity(PedidoDTO dto);

    @AfterMapping
    default void mapClienteNome(Pedido entity, @MappingTarget PedidoDTO dto) {
        if (entity.getCliente() != null) {
            dto.setClienteNome(entity.getCliente().getNome());
        }
    }
    
}
