package com.shop.vendasonline.controller;

import java.util.List;
import java.util.Optional;

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
import com.shop.vendasonline.dto.ProdutoDTO;
import com.shop.vendasonline.mapper.ProdutoMapper;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Produto;
import com.shop.vendasonline.service.PedidoService;
import com.shop.vendasonline.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;
    private final ProdutoMapper produtoMapper;
    
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@Valid @RequestBody ProdutoDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        
        if (dto.getPedido() == null || dto.getPedido().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Pedido pedido = pedidoService.findPedidoById(dto.getPedido().getId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        produto.setPedido(pedido);

        Produto salvo = produtoService.saveProduto(produto);
        ProdutoDTO responseDto = produtoMapper.toDto(salvo);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findProdutoById(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.findAllProdutos());
    }
    
    @GetMapping("/count-produtos-por-pedido/{pedidoId}")
    public ResponseEntity<Long> countProdutosPorPedido(@PathVariable Long pedidoId) {
        long total = produtoService.countProdutosPorPedido(pedidoId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/sum-preco-produtos-por-pedido/{pedidoId}")
    public ResponseEntity<Double> sumPrecoProdutosPorPedido(@PathVariable Long pedidoId) {
        Double total = produtoService.sumPrecoProdutosPorPedido(pedidoId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Produto>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return ResponseEntity.ok(produtoService.findAllProdutos(pageable));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        try {
            Produto produto = produtoService.updateProduto(produtoAtualizado);
            return ResponseEntity.ok(produto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        produtoService.deleteAllProdutos();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/codigo")
    public ResponseEntity<Produto> buscarPorCodigo(@RequestParam String codigo) {
        Produto produto = produtoService.findByCodigo(codigo);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/contar")
    public ResponseEntity<Long> contarProdutos() {
        return ResponseEntity.ok(produtoService.countProdutos());
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> verificarExistenciaPorCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(produtoService.existsByCodigo(codigo));
    }

}
