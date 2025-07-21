package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.ProdutoDTO;
import com.shop.vendasonline.model.Produto;

@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface ProdutoMapper {

    @Mapping(source = "pedido.numeroPedido", target = "numeroPedido")
    @Mapping(source = "pedido.cliente.id", target = "clienteId")
    @Mapping(source = "pedido.cliente.nome", target = "clienteNome")
    ProdutoDTO toDto(Produto entity);

    @Mapping(target = "pedido", ignore = true)
    Produto toEntity(ProdutoDTO dto);
    
}
