package com.shop.vendasonline.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Produto;
import com.shop.vendasonline.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> findProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> findAllProdutos() {
        return produtoRepository.findAll();
    }

    public Page<Produto> findAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Transactional
    public Produto updateProduto(Produto produto) {
        Produto produtoExistente = produtoRepository.findById(produto.getId())
            .orElseThrow(() -> new IllegalArgumentException("Produto not found with id: " + produto.getId()));
        
        produtoExistente.setCodigo(produto.getCodigo());
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setDesconto(produto.getDesconto());
        
        return produtoRepository.save(produtoExistente);
    }

    public Produto findByCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    public long countProdutos() {
        return produtoRepository.count();
    }

    public long countProdutosPorPedido(Long pedidoId) {
        return produtoRepository.countProdutosPorPedido(pedidoId);
    }

    public double sumPrecoProdutosPorPedido(Long pedidoId) {
        Double total = produtoRepository.sumPrecoProdutosPorPedido(pedidoId);
        return total != null ? total : 0.0;
    }
    
    public void deleteAllProdutos() {
        produtoRepository.deleteAll();
    }

    public boolean existsByCodigo(String codigo) {
        return produtoRepository.existsByCodigo(codigo);
    }

}
