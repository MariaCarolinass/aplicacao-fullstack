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
import com.shop.vendasonline.model.Produto;
import com.shop.vendasonline.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    @PostMapping
    public ResponseEntity<Void> criarProduto(@Valid @RequestBody Produto produto) {
        produtoService.saveProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.findProdutoById(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.findAllProdutos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        try {
            produtoService.updateProduto(produtoAtualizado);
            return ResponseEntity.ok().build();
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
