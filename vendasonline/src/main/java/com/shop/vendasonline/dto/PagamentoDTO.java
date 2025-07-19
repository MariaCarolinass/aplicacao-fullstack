package com.shop.vendasonline.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PagamentoDTO {
    
    private Long id;
    private String metodoPagamento;
    private String numeroCartao;
    private String bandeiraCartao;
    private Integer codigoSegurancaCartao;
    private String nomeTitularCartao;
    private String cpfTitularCartao;
    private LocalDate dataPagamento;
    private Long vendaId;

}
