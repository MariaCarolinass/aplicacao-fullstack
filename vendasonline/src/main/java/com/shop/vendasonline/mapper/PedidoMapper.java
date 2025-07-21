package com.shop.vendasonline.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.shop.vendasonline.dto.PedidoDTO;
import com.shop.vendasonline.model.Pedido;

@Mapper(componentModel = "spring")
public abstract class PedidoMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    public abstract PedidoDTO toDto(Pedido entity);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    @Mapping(target = "venda", ignore = true)
    public abstract Pedido toEntity(PedidoDTO dto);

    @AfterMapping
    protected void mapClienteNome(Pedido entity, @MappingTarget PedidoDTO dto) {
        if (entity.getCliente() != null) {
            dto.setClienteNome(entity.getCliente().getNome());
        }
    }
}
