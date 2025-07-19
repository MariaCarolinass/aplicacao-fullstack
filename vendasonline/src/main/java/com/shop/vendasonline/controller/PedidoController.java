package com.shop.vendasonline.controller;

import java.util.List;
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
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Status;
import com.shop.vendasonline.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.savePedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.findPedidoById(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.findAllPedidos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedidoAtualizado) {
        pedidoAtualizado.setId(id);
        try {
            pedidoService.updatePedido(pedidoAtualizado);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        pedidoService.deleteAllPedidos();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public ResponseEntity<List<Pedido>> buscarPorStatus(@RequestParam Status status) {
        return ResponseEntity.ok(pedidoService.findByStatus(status));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.findByClienteId(clienteId));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Pedido>> buscarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(pedidoService.findByProdutoId(produtoId));
    }

    @GetMapping("/numero")
    public ResponseEntity<Pedido> buscarPorNumero(@RequestParam String numeroPedido) {
        Pedido pedido = pedidoService.findByNumeroPedido(numeroPedido);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> contarTodos() {
        return ResponseEntity.ok(pedidoService.count());
    }

    @GetMapping("/contar/status")
    public ResponseEntity<Long> contarPorStatus(@RequestParam Status status) {
        return ResponseEntity.ok(pedidoService.countByStatus(status));
    }

    @GetMapping("/contar/cliente/{clienteId}")
    public ResponseEntity<Long> contarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.countByClienteId(clienteId));
    }

    @GetMapping("/soma")
    public ResponseEntity<Double> somarValorTotal() {
        return ResponseEntity.ok(pedidoService.sumValorTotal());
    }

    @GetMapping("/recentes")
    public ResponseEntity<List<Pedido>> pedidosMaisRecentes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(pedidoService.encontrarPedidosMaisRecentes(page, size));
    }

}
