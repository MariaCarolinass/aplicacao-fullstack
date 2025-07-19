package com.shop.vendasonline.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    @Column(name = "numero_cartao", nullable = false)
    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;

    @Column(name = "codigo_seguranca_cartao", nullable = false)
    private Integer codigoSegurancaCartao;

    @Column(name = "nome_titular_cartao", nullable = false)
    private String nomeTitularCartao;

    @Column(name = "cpf_titular_cartao", nullable = false)
    private String cpfTitularCartao;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @OneToOne
    @JoinColumn(name = "venda_id", nullable = false, unique = true)
    private Venda venda;

}
