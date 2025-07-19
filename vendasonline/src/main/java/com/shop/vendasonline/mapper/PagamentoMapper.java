package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.PagamentoDTO;
import com.shop.vendasonline.model.Pagamento;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    
    @Mapping(source = "venda.id", target = "vendaId")
    PagamentoDTO toDto(Pagamento entity);

    @Mapping(target = "venda", ignore = true)
    Pagamento toEntity(PagamentoDTO dto);

}
