package com.shop.vendasonline.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.vendasonline.dto.RelatorioDTO;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Venda;
import com.shop.vendasonline.service.RelatorioService;
import com.shop.vendasonline.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final RelatorioService relatorioService;
    
    @PostMapping
    public ResponseEntity<Void> criarVenda(@Valid @RequestBody Venda venda) {
        vendaService.saveVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable Long id) {
        Venda venda = vendaService.findVendaById(id);
        return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarVenda(@PathVariable Long id, @Valid @RequestBody Venda vendaAtualizada) {
        vendaAtualizada.setId(id);
        try {
            vendaService.updateVenda(vendaAtualizada);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.deleteVenda(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodas() {
        vendaService.deleteAllVendas();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {
        return ResponseEntity.ok(vendaService.findAllVendas());
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Venda>> listarTodas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataVenda") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return ResponseEntity.ok(vendaService.findAllVendas(pageable));
    }

    @GetMapping("/mais-recentes")
    public ResponseEntity<List<Venda>> vendasMaisRecentes(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(vendaService.encontrarVendasMaisRecentes(page, size));
    }

    @GetMapping("/mais-antigas")
    public ResponseEntity<List<Venda>> vendasMaisAntigas(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(vendaService.encontrarVendasMaisAntigas(page, size));
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> contarVendas() {
        return ResponseEntity.ok(vendaService.countVendas());
    }

    @GetMapping("/relatorio")
    public ResponseEntity<RelatorioDTO> gerarRelatorio() {
        return ResponseEntity.ok(relatorioService.gerarRelatorio());
    }

    @GetMapping("/relatorio/pedidos-pendentes")
    public ResponseEntity<List<Pedido>> listarPedidosPendentes() {
        return ResponseEntity.ok(relatorioService.listarPedidosPendentes());
    }

    @GetMapping("/relatorio/clientes-mais-ativos")
    public ResponseEntity<List<Cliente>> clientesMaisAtivos(@RequestParam(defaultValue = "5") int topN) {
        return ResponseEntity.ok(relatorioService.clientesMaisAtivos(topN));
    }

    @GetMapping("/relatorio/total-clientes")
    public ResponseEntity<Long> totalClientes() {
        return ResponseEntity.ok(relatorioService.totalClientes());
    }
    
}
