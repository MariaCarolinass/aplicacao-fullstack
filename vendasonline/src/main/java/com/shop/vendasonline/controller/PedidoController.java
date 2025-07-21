package com.shop.vendasonline.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import com.shop.vendasonline.dto.PedidoDTO;
import com.shop.vendasonline.mapper.PedidoMapper;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Status;
import com.shop.vendasonline.service.ClienteService;
import com.shop.vendasonline.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    
    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final PedidoMapper pedidoMapper;
    
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@Valid @RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoMapper.toEntity(dto);

        if (dto.getCliente() == null || dto.getCliente().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente = clienteService.findClienteById(dto.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoService.savePedido(pedido);
        PedidoDTO responseDto = pedidoMapper.toDto(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.findPedidoById(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        List<Pedido> pedidos = pedidoService.findAllPedidos();
        List<PedidoDTO> dtoList = pedidos.stream()
            .map(pedidoMapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Pedido>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "numeroPedido") String sortBy
    ) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
    return ResponseEntity.ok(pedidoService.findAllPedidos(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedidoAtualizado) {
        pedidoAtualizado.setId(id);
        try {
            Pedido pedido = pedidoService.updatePedido(pedidoAtualizado);
            return ResponseEntity.ok(pedido);
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

    @GetMapping("/numero")
    public ResponseEntity<Pedido> buscarPorNumero(@RequestParam Integer numeroPedido) {
        Pedido pedido = pedidoService.findByNumeroPedido(numeroPedido);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> contarTodos() {
        return ResponseEntity.ok(pedidoService.count());
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
