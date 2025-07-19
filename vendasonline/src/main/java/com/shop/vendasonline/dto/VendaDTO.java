package com.shop.vendasonline.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class VendaDTO {
    
    private Long id;
    private LocalDate dataVenda;
    private LocalDate dataCancelamento;
    private String motivoCancelamento;
    private String observacoes;
    private Long pedidoId;

}
