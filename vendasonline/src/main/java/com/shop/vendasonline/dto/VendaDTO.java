package com.shop.vendasonline.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class VendaDTO {
    
    private Long id;

    @NotNull(message = "A data da venda não pode ser nula")
    @PastOrPresent(message = "A data da venda não pode estar no futuro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataVenda;

    @NotNull(message = "A data de cancelamento não pode ser nula")
    @PastOrPresent(message = "A data de cancelamento não pode estar no futuro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCancelamento;

    private String motivoCancelamento;
    
    private String observacoes;
    
    private Long pedidoId;

}
