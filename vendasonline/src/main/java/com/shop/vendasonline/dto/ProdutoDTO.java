package com.shop.vendasonline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDTO {
    
    private Long id;

    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    private Double preco;

    private Double desconto;

    private Long clienteId;

    private Integer numeroPedido;

    private PedidoDTO pedido;

}
