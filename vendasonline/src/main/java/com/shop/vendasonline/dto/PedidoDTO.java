package com.shop.vendasonline.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPedido;
    
    private String observacoes;

    private String status;

    private Long clienteId;

}
