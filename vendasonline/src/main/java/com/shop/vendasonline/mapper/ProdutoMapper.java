package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shop.vendasonline.dto.ProdutoDTO;
import com.shop.vendasonline.model.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    
    ProdutoDTO toDto(Produto entity);
    
    @Mapping(target = "pedido", ignore = true)
    Produto toEntity(ProdutoDTO dto);

}
