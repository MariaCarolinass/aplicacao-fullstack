package com.shop.vendasonline.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Status;
import com.shop.vendasonline.repository.ClienteRepository;
import com.shop.vendasonline.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> findPedidoById(Long id) {
        return pedidoRepository.findById(id);
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

    public Page<Pedido> findAllPedidos(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Transactional
    public Pedido updatePedido(Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(pedido.getId())
            .orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + pedido.getId()));
        
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        
        pedidoExistente.setCliente(cliente);
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setDataPedido(pedido.getDataPedido());
        pedidoExistente.setNumeroPedido(pedido.getNumeroPedido());
        pedidoExistente.setObservacoes(pedido.getObservacoes());

        return pedidoRepository.save(pedidoExistente);
    }

    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Pedido findByNumeroPedido(Integer numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido);
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
