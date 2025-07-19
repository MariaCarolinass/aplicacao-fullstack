package com.shop.vendasonline.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Status;
import com.shop.vendasonline.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> findByStatus(Status status) {
        return pedidoRepository.findByStatus(status);
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
    }

    public void updatePedido(Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + pedido.getId()));
        
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setCliente(pedido.getCliente());
        pedidoExistente.setProdutos(pedido.getProdutos());
        pedidoExistente.setDataPedido(pedido.getDataPedido());
        pedidoExistente.setNumeroPedido(pedido.getNumeroPedido());
        pedidoExistente.setObservacoes(pedido.getObservacoes());
        pedidoExistente.setVenda(pedido.getVenda());

        pedidoRepository.save(pedidoExistente);
    }

    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> findByProdutoId(Long produtoId) {
        return pedidoRepository.findByProdutoId(produtoId);
    }

    public Pedido findByNumeroPedido(String numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido);
    }

    public long countByStatus(Status status) {
        return pedidoRepository.countByStatus(status);
    }

    public long count() {
        return pedidoRepository.count();
    }

    public long countByClienteId(Long clienteId) {
        return pedidoRepository.countByClienteId(clienteId);
    }

    public Double sumValorTotal() {
        return pedidoRepository.sumValorTotal();
    }

    public List<Pedido> encontrarPedidosMaisRecentes(int page, int size) {
        return pedidoRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public void deleteAllPedidos() {
        pedidoRepository.deleteAll();
    }

}
