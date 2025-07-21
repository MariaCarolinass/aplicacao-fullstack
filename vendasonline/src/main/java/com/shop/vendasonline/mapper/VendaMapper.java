package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.VendaDTO;
import com.shop.vendasonline.model.Venda;

@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface VendaMapper {
    
    @Mapping(source = "pedido.numeroPedido", target = "numeroPedido")
    @Mapping(source = "pedido.cliente.id", target = "clienteId")
    VendaDTO toDto(Venda entity);
    
    Venda toEntity(VendaDTO dto);

}
