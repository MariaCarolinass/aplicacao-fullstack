package com.shop.vendasonline.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.vendasonline.dto.RelatorioDTO;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.service.RelatorioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/relatorio")
public class RelatorioControlller {   

    private final RelatorioService relatorioService;
    
    @GetMapping
    public ResponseEntity<RelatorioDTO> gerarRelatorio() {
        return ResponseEntity.ok(relatorioService.gerarRelatorio());
    }

    @GetMapping("/pedidos-pendentes")
    public ResponseEntity<List<Pedido>> listarPedidosPendentes() {
        return ResponseEntity.ok(relatorioService.listarPedidosPendentes());
    }

    @GetMapping("/clientes-mais-ativos")
    public ResponseEntity<List<Cliente>> clientesMaisAtivos(@RequestParam(defaultValue = "5") int topN) {
        return ResponseEntity.ok(relatorioService.clientesMaisAtivos(topN));
    }

    @GetMapping("/total-clientes")
    public ResponseEntity<Long> totalClientes() {
        return ResponseEntity.ok(relatorioService.totalClientes());
    }

}
