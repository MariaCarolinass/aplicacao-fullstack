package com.shop.vendasonline.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PedidoDTO {
    
    private Long id;
    private String numeroPedido;
    private LocalDate dataPedido;
    private String observacoes;
    private String status;
    private Long clienteId;

}
