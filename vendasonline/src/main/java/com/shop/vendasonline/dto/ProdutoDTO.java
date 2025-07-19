package com.shop.vendasonline.dto;
import lombok.Data;

@Data
public class ProdutoDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Double desconto;
    private Long pedidoId;

}
