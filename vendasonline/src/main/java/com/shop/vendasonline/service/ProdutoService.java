package com.shop.vendasonline.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Produto;
import com.shop.vendasonline.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public void saveProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public Produto findProdutoById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> findAllProdutos() {
        return produtoRepository.findAll();
    }

    public void updateProduto(Produto produto) {
        Produto produtoExistente = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto not found with id: " + produto.getId()));
        
        produtoExistente.setCodigo(produto.getCodigo());
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setDesconto(produto.getDesconto());
        produtoExistente.setPedido(produto.getPedido());
        
        produtoRepository.save(produtoExistente);
    }

    public Produto findByCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    public List<Produto> findByPedidoId(Long pedidoId) {
        return produtoRepository.findByPedidoId(pedidoId);
    }

    public long countProdutos() {
        return produtoRepository.count();
    }

    public void deleteAllProdutos() {
        produtoRepository.deleteAll();
    }

    public boolean existsByCodigo(String codigo) {
        return produtoRepository.existsByCodigo(codigo);
    }

}
