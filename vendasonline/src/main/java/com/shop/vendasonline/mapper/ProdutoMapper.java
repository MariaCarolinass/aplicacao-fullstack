package com.shop.vendasonline.mapper;

import org.mapstruct.Mapper;
import com.shop.vendasonline.dto.ProdutoDTO;
import com.shop.vendasonline.model.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    
    ProdutoDTO toDto(Produto entity);

    Produto toEntity(ProdutoDTO dto);

}
