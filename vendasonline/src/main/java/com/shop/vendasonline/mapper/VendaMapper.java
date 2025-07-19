package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.VendaDTO;
import com.shop.vendasonline.model.Venda;

@Mapper(componentModel = "spring")
public interface VendaMapper {
    
    @Mapping(source = "pedido.id", target = "pedidoId")
    VendaDTO toDto(Venda entity);

    @Mapping(target = "pedido", ignore = true)
    Venda toEntity(VendaDTO dto);

}
