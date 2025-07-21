package com.shop.vendasonline.dto;

import java.time.LocalDate;
import com.shop.vendasonline.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class PedidoDTO {
    
    private Long id;

    @NotNull(message = "O número do pedido é obrigatório")
    private Integer numeroPedido;

    @NotNull(message = "A data do pedido não pode ser nula")
    @PastOrPresent(message = "A data do pedido não pode estar no futuro")
    private LocalDate dataPedido;
    
    private String observacoes;

    private Status status;

    private Long clienteId;
    
    private String clienteNome;

    private ClienteDTO cliente;
    
}
